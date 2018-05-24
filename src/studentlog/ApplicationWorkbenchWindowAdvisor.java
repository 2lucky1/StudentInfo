package studentlog;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.part.EditorInputTransfer;

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
		
				configurer.addEditorAreaTransfer(EditorInputTransfer.getInstance());
				configurer.configureEditorAreaDropListener(new DropTargetListener() {
					
					private IWorkbenchWindow window = configurer.getWindow();
		
		//			public DropTargetListener(IWorkbenchWindow window) {
		//				this.window = window;
		//			}
		
					@Override
					public void dragEnter(DropTargetEvent event) {
						event.detail = DND.DROP_COPY;
						event.feedback = DND.FEEDBACK_SELECT;
					}
		
					@Override
					public void dragLeave(DropTargetEvent event) {
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

//		 				StudentsEntry entry = new Gson().fromJson((String) event.data, StudentsEntry.class);
//						StudentProfileEditorInput input = new StudentProfileEditorInput(entry.getName());
//		
//		 					event.printStackTrace();
		 				}

		
					@Override
					public void dropAccept(DropTargetEvent event) {
						// TODO Auto-generated method stub
						
					}
				});
		 //		DropTarget dt = new DropTarget(co, DND.DROP_MOVE);
		 //		dt.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		
//		IWorkbenchPage page = configurer.getWindow().getActivePage();
//		configurer.addEditorAreaTransfer(TextTransfer.getInstance());
//		configurer.configureEditorAreaDropListener(new DropTargetAdapter() {
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
