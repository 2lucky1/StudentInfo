package studentlog.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import studentlog.editors.StudentProfileEditor;
import studentlog.editors.StudentProfileEditorInput;
import studentlog.model.StudentsEntry;
import studentlog.views.StudentsView;

public class OpenProfileHandler extends AbstractHandler {

	public static final String ID = "studentlog.commands.OpenProfile";
	private IWorkbenchWindow window;
	private ISelection selection;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		StudentsView view = (StudentsView) page.findView(StudentsView.ID);
		
		selection = view.getSite().getSelectionProvider().getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			Object item = ((IStructuredSelection) selection).getFirstElement();
			if (item != null) {
				StudentsEntry entry = (StudentsEntry) item;
				StudentProfileEditorInput input = new StudentProfileEditorInput(entry.getName());
				try {					
					StudentProfileEditor editor = (StudentProfileEditor)page.openEditor(input, StudentProfileEditor.ID);
					editor.fillEditorArea(entry);
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
}
