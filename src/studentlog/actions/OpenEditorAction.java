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
		setText("&Add Contact...");
		setToolTipText("Add a contact to your contacts list.");
//		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Const.BUNDLE_ID, IImageKeys.OFFICE_GUY));
		window.getSelectionService().addSelectionListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionFactory.IWorkbenchAction#dispose()
	 */
	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.
	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		// selection containing elements
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			super.setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof StudentsEntry);

		} else {
			// other selections (e.g., containing text or of other kinds)
			super.setEnabled(false);
		}

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
