package studentlog.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import studentlog.model.ITreeItem;
import studentlog.views.StudentsView;

public class DeleteHandler extends AbstractHandler implements ISelectionChangedListener {
	private static final String ID = "studentlog.commands.delete";
	
	private IWorkbenchWindow window;
	private TreeViewer treeViewer;
	private ISelection selection;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		StudentsView view = (StudentsView) page.findView(StudentsView.ID);
		treeViewer = view.getTreeViewer();
		
		selection = treeViewer.getSelection();

		if (selection != null && selection instanceof IStructuredSelection) {
			Object item = ((IStructuredSelection) selection).getFirstElement();
			if (item instanceof ITreeItem) {
				ITreeItem parent = ((ITreeItem) item).getParent();
				parent.getChildren().remove(item);
				treeViewer.refresh();
			}
		}
		return null;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
	}
}
