package studentlog.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import studentlog.editors.StudentProfileEditor;
import studentlog.editors.StudentProfileEditorInput;
import studentlog.model.StudentsEntry;
import studentlog.views.StudentsView;

public class OpenEditorAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

	public static final String ID = "studentlog.actions.OpenEditorAction";
	private IWorkbenchWindow window;
	private IStructuredSelection selection;

	/**
	 * @param window
	 */
	public OpenEditorAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Open editor...");
		setToolTipText("Open editor for selected item");
//		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Const.BUNDLE_ID, IImageKeys.OFFICE_GUY));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {

	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		StudentsView view = (StudentsView) page.findView(StudentsView.ID);

		selection = (IStructuredSelection) view.getSite().getSelectionProvider().getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			Object item = ((IStructuredSelection) selection).getFirstElement();
			if (item != null) {
				StudentsEntry entry = (StudentsEntry) item;
				StudentProfileEditorInput input = new StudentProfileEditorInput(entry);
				try {
					StudentProfileEditor editor = (StudentProfileEditor) page.openEditor(input,
							StudentProfileEditor.ID);
					editor.fillEditorArea(entry);
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
