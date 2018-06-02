package studentlog.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.EditorPart;

import com.google.gson.Gson;

import studentlog.model.StudentsEntry;
import studentlog.model.TreeModel;
import studentlog.ui.StudentProfileEditorPanel;

public class StudentProfileEditor extends EditorPart {
	public static String ID = "studentlog.editors.studentprofile";

	private StudentProfileEditorPanel panel;

	public StudentProfileEditor() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(getUser());

	}

	@Override
	public void createPartControl(Composite parent) {
		panel = new StudentProfileEditorPanel(parent, SWT.NONE);
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		try {
			Object item = page.showView("studentlog.views.students").getSite().getSelectionProvider().getSelection();
			System.out.println("selected item: " + item);
			if(item!=null && item instanceof StudentsEntry) {
				fillEditorArea((StudentsEntry)item);
			}
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		TreeViewer treeViewer = new TreeViewer();
//		treeViewer.setInput(TreeModel.getInstance().getRoot());
		
//		DropTarget dt = new DropTarget(panel, DND.DROP_MOVE);
//		dt.setTransfer(new Transfer[] { TextTransfer.getInstance() });
//		dt.addDropListener(new DropTargetAdapter() {
//			public void drop(DropTargetEvent event) {
//				StudentsEntry entry = new Gson().fromJson((String) event.data, StudentsEntry.class);
//				StudentProfileEditorInput input = new StudentProfileEditorInput(entry.getName());
//				StudentProfileEditor newEditor;
//				try {
//					newEditor = (StudentProfileEditor) page.openEditor(input, StudentProfileEditor.ID);
//					newEditor.getPanel().fillPanelArea(entry);
//				} catch (PartInitException e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	public void fillEditorArea(StudentsEntry entry) {
		panel.fillPanelArea(entry);

	}

	public StudentProfileEditorPanel getPanel() {
		return panel;
	}

	@Override
	public void setFocus() {
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUser() {
		return ((StudentProfileEditorInput) getEditorInput()).getName();
	}

}
