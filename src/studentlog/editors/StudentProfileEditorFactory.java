package studentlog.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import studentlog.model.StudentsEntry;
import studentlog.views.StudentsView;

public class StudentProfileEditorFactory implements IElementFactory {

	@Override
	public IAdaptable createElement(IMemento memento) {
//		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		IWorkbenchPage page = window.getActivePage();
//		StudentsView view = (StudentsView) page.findView(StudentsView.ID);
//		IStructuredSelection selection = (IStructuredSelection) view.getSite()
//				.getSelectionProvider()
//				.getSelection();
		
//		StudentsEntry studentsEntry = (StudentsEntry) selection.getFirstElement();
//		StudentsEntry studentsEntry = null;
		StudentProfileEditorInput input = new StudentProfileEditorInput(null);
		return input;
		// return null;
	}

}