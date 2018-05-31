package studentlog.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

import studentlog.model.StudentsEntry;

public class StudentProfileEditorFactory implements IElementFactory {

	@Override
	public IAdaptable createElement(IMemento memento) {
		StudentsEntry studentsEntry = TreeController.getInstance().findNodeByFullPath(memento.getString(NodeEditorInput.FEATURE_ID));
		NodeEditorInput input = new NodeEditorInput(node);
		return input;
	}

}