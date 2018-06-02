package studentlog.dnd;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.IHandlerService;

import studentlog.commands.OpenProfileHandler;
import studentlog.views.StudentsView;

public class MyDropListener implements DropTargetListener {

	private IWorkbenchWindow window;

	public MyDropListener(IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void dragEnter(DropTargetEvent event) {
		event.detail = DND.DROP_COPY;
		event.feedback = DND.FEEDBACK_SELECT;
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
		event.detail = DND.DROP_COPY;
		event.feedback = DND.FEEDBACK_NONE;
	}

	@Override
	public void dragOver(DropTargetEvent event) {
		event.detail = DND.DROP_COPY;
		event.feedback = DND.FEEDBACK_SELECT;
	}

	@Override
	public void drop(DropTargetEvent event) {
		StudentsView view = (StudentsView)window.getActivePage().findView(StudentsView.ID);
		IHandlerService handlerService = view.getSite().getService(IHandlerService.class);
		try {
			handlerService.executeCommand(OpenProfileHandler.ID, null);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
	}
}
