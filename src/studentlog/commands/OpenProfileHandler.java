package studentlog.commands;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import studentlog.Application;
import studentlog.ImageKeys;
import studentlog.editors.StudentProfileEditor;
import studentlog.editors.StudentProfileEditorInput;
import studentlog.model.StudentsEntry;
import studentlog.model.StudentsGroup;
import studentlog.views.StudentsView;

public class OpenProfileHandler extends AbstractHandler {

	private IWorkbenchWindow window;
	private final static String ID = "studentlog.action.student_profile_actoin";
	private IStructuredSelection selection;

	
//	public OpenProfileHandler(IWorkbenchWindow window) {
//		super();
//		this.window = window;
////		setId(ID);
////		setText("&Chat");
////		setToolTipText("Profile of the selected student");
////		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,
////				ImageKeys.STUDENT_ICON.getFilePath()));
//		window.getSelectionService().addSelectionListener(this);
//	}


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		this.window = window;
//		System.out.println("open editor");
//		Object item = selection.getFirstElement();
//		StudentsEntry entry = (StudentsEntry) item;
//		IWorkbenchPage page = window.getActivePage();
//		StudentProfileEditorInput input = new StudentProfileEditorInput(entry.getName());
//		try {
//			page.openEditor(input, StudentProfileEditor.ID);
//		} catch (PartInitException e) {
//			// Handle error.
//		}
//		return null;
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		Control control = null;
		StudentsView view = (StudentsView) page.findView(StudentsView.ID);
		// Get the selection
		ISelection selection = view.getSite().getSelectionProvider()
				.getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			// If we had a selection lets open the editor
			if (obj != null) {
//				Person person = (Person) obj;
//				MyPersonEditorInput input = new MyPersonEditorInput(
//						person.getId());
				StudentsEntry entry = (StudentsEntry) obj;
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


//	@Override
//	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
//		// TODO Auto-generated method stub
//		if (incoming instanceof IStructuredSelection) {
//			this.selection = (IStructuredSelection) incoming;
//			super.setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof StudentsGroup);
//			
//		} else {
//			// other selections (e.g., containing text or of other kinds)
//			super.setEnabled(false);
//		}
//		
//	}

	

}
