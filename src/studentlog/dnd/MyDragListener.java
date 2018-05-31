package studentlog.dnd;

import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.EditorInputTransfer;

import studentlog.editors.StudentProfileEditor;
import studentlog.editors.StudentProfileEditorInput;
import studentlog.model.StudentsEntry;


public class MyDragListener implements DragSourceListener {

	private TreeViewer treeViewer;
	
	public MyDragListener(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}
	
	
	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("Drag start");

	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		if (EditorInputTransfer.getInstance().isSupportedType(event.dataType)) { // if
			// support
			// EditorInputTransfer
			IStructuredSelection selection = treeViewer.getStructuredSelection();
			int i = 0;
			EditorInputTransfer.EditorInputData[] arrData = new EditorInputTransfer.EditorInputData[selection.size()];
			Iterator<?> iterator = selection.iterator();
			while (iterator.hasNext()) {
				StudentsEntry entry = (StudentsEntry) iterator.next();
				IEditorInput input = new StudentProfileEditorInput(entry);
				arrData[i] = EditorInputTransfer.createEditorInputData(StudentProfileEditor.ID, input);
				i++;
			}
			event.data = arrData;
		}
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		if (event.detail == DND.DROP_MOVE) {
			System.out.println("Finished Drag");
		}

	}

}
