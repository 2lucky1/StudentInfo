package studentlog;

import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.google.gson.Gson;

import studentlog.editors.StudentProfileEditor;
import studentlog.editors.StudentProfileEditorInput;
import studentlog.model.StudentsEntry;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(900, 450));
		configurer.setShowCoolBar(true);
		configurer.setShowPerspectiveBar(true);
		configurer.setShowStatusLine(true);
		configurer.setTitle("StudentLog");
		
		IWorkbenchPage page = configurer.getWindow().getActivePage();
		configurer.addEditorAreaTransfer(TextTransfer.getInstance());
		configurer.configureEditorAreaDropListener(new DropTargetAdapter() {
			public void drop(DropTargetEvent event) {
				StudentsEntry entry = new Gson().fromJson((String) event.data, StudentsEntry.class);
				StudentProfileEditorInput input = new StudentProfileEditorInput(entry.getName());
				StudentProfileEditor newEditor;
				try {
					newEditor = (StudentProfileEditor) page.openEditor(input, StudentProfileEditor.ID);
					newEditor.getPanel().fillPanelArea(entry);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
//		DropTarget dt = new DropTarget(co, DND.DROP_MOVE);
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
}
