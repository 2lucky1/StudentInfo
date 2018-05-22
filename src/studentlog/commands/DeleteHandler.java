package studentlog.commands;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import studentlog.editors.StudentProfileEditor;
import studentlog.editors.StudentProfileEditorInput;
import studentlog.model.Folder;
import studentlog.model.ITreeItem;
import studentlog.model.Root;
import studentlog.model.StudentsEntry;
import studentlog.model.StudentsGroup;
import studentlog.model.TreeModel;
import studentlog.views.StudentsView;

public class DeleteHandler extends AbstractHandler implements ISelectionChangedListener {
	private IWorkbenchWindow window;
	private final static String ID = "studentlog.action.student_profile_actoin";
	private IStructuredSelection selection;
	
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		StudentsView view = (StudentsView) page.findView(StudentsView.ID);
		// Get the selection
		ISelection selection = view.getSite().getSelectionProvider()
				.getSelection();
		Root root = TreeModel.getInstance().getRoot();

		
//		TreeViewer treeViewer = view.getTreeViewer();
//		IStructuredSelection selectedItem = (IStructuredSelection)treeViewer.getTree().getSelection()[0];
//		if(selectedItem==null || selectedItem.isEmpty()) {
//			System.out.println("Item is undefined");
//			return null;
//		}else {
//			treeViewer.remove(selectedItem);
//		}		
		
		
		if (selection != null && selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if(obj instanceof ITreeItem) {
				System.out.println("Delete operation");
				
				TreeModel.getInstance().setRoot(removeItemFromRoot(root, (ITreeItem)obj));
				ITreeItem parent = ((ITreeItem) obj).getParent();
				parent.getChildren().remove(obj);
				System.out.println(parent);
			}
		}
		return null;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
	}
	
	public Root removeItemFromRoot(Root root, ITreeItem item) {
		List<Folder> folders = root.getChildren();
		for(Folder folder : folders) {
			if(folder.getName().equals(item.getName())) {
				folders.remove(item);
				return root;
			}
			for(StudentsGroup studentsGroup : folder.getChildren()) {
				if(studentsGroup.getName().equals(item.getName())) {
					folder.getChildren().remove(studentsGroup);
					return root;
				}
				for(StudentsEntry studentsEntry : studentsGroup.getChildren()) {
					if(studentsEntry.getName().equals(item.getName())) {
						studentsGroup.getChildren().remove(item);
						return root;
					}
				}
			}
		}
		return root;
	}

}
