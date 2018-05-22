package studentlog.commands;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import studentlog.dialogs.AddGroupDialog;
import studentlog.dialogs.AddStudentDialog;
import studentlog.model.Folder;
import studentlog.model.ITreeItem;
import studentlog.model.Root;
import studentlog.model.StudentsEntry;
import studentlog.model.StudentsGroup;
import studentlog.model.TreeModel;
import studentlog.views.StudentsView;

public class AddStudentHandler extends AbstractHandler implements ISelectionListener {

	private static final String GROUP = "Group";
	private IStructuredSelection selection;
	private Root root;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Add menu");

		root = TreeModel.getInstance().getRoot();

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		StudentsView view = (StudentsView) page.findView(StudentsView.ID);
		// Get the selection
		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		Object item = ((IStructuredSelection) selection).getFirstElement();
		if (item instanceof Folder) {
			AddGroupDialog agd = new AddGroupDialog(window.getShell());
			
			int code = agd.open();
			if (code == Window.OK) {
				Folder folder = (Folder) item;
				String groupName = GROUP + agd.getGroupNumber();
				StudentsGroup studentsGroup = new StudentsGroup((Folder) item, groupName);
				folder.addEntry(studentsGroup);
//				newRoot = addItemToRoot(folder, studentsGroup);
//				TreeModel.getInstance().setRoot(newRoot);
				view.update(root);
				return null;
			}
		} else if (item instanceof StudentsGroup) {
			AddStudentDialog asd = new AddStudentDialog(window.getShell());

			int code = asd.open();
			if (code == Window.OK) {
				StudentsGroup group = (StudentsGroup) item;
				StudentsEntry entry = new StudentsEntry(asd.getName(), group.getName(), asd.getAddress(), asd.getCity(),
						asd.getResult(), group);
				group.addEntry(entry);
				view.update(root);
//				newRoot = addItemToRoot(group, entry);
//				TreeModel.getInstance().setRoot(newRoot);
				return null;
			}
		}
		return null;
	}

	public Root addItemToRoot(ITreeItem selectedItem, ITreeItem addedItem) {
		List<Folder> folders = root.getChildren();
		for (Folder folder : folders) {
			if (folder.getName().equals(selectedItem.getName())) {
				folder.addEntry((StudentsGroup) addedItem);
				return root;
			}
			for (StudentsGroup studentsGroup : folder.getChildren()) {
				if (studentsGroup.getName().equals(selectedItem.getName())) {
					studentsGroup.addEntry((StudentsEntry) addedItem);
					return root;
				}
			}
		}
		return root;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {

	}
}
