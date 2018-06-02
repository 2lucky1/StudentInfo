package studentlog.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import studentlog.dialogs.AddGroupDialog;
import studentlog.dialogs.AddStudentDialog;
import studentlog.model.Folder;
import studentlog.model.StudentsEntry;
import studentlog.model.StudentsGroup;
import studentlog.views.StudentsView;

public class AddStudentHandler extends AbstractHandler implements ISelectionListener {

	private static final String GROUP = "Group";
	private TreeViewer treeViewer;
	private ISelection selection;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		StudentsView view = (StudentsView) page.findView(StudentsView.ID);
		
		treeViewer = view.getTreeViewer();
		
		selection = treeViewer.getSelection();
		
		Object item = ((IStructuredSelection) selection).getFirstElement();
		if (item instanceof Folder) {
			AddGroupDialog agd = new AddGroupDialog(window.getShell());
			int code = agd.open();
			if (code == Window.OK) {
				Folder folder = (Folder) item;
				String groupName = GROUP + agd.getGroupNumber();
				StudentsGroup studentsGroup = new StudentsGroup((Folder) item, groupName);
				folder.addEntry(studentsGroup);
				treeViewer.refresh();
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
				treeViewer.refresh();
				return null;
			}
		}
		return null;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {

	}
}
