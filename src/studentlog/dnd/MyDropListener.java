package studentlog.dnd;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorInputTransfer;

import studentlog.editors.StudentProfileEditor;

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
		IWorkbenchPage page = window.getActivePage();
		System.out.println("----Drop-----");
		System.out.println(event.data);
		EditorInputTransfer.EditorInputData[] editorInputs = (EditorInputTransfer.EditorInputData[]) event.data;

		for (int i = 0; i < editorInputs.length; i++) {
			IEditorInput input = editorInputs[i].input;
			try {
				page.openEditor(input, StudentProfileEditor.ID);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

}
