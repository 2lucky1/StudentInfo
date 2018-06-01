package studentlog.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

import studentlog.model.StudentsEntry;
import studentlog.model.TreeModel;

public class StudentProfileEditorFactory implements IElementFactory {

	@Override
	public IAdaptable createElement(IMemento memento) {
		StudentsEntry studentsEntry = (StudentsEntry) TreeModel.getInstance().findNodeByFullPath(memento.getString(StudentProfileEditorInput.FEATURE_ID));
		StudentProfileEditorInput input = new StudentProfileEditorInput(studentsEntry);
		return input;
	}

}