package studentlog.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import studentlog.model.StudentsEntry;

public class StudentProfileEditorInput implements IEditorInput {
	private final StudentsEntry studentsEntry;

	public StudentProfileEditorInput(StudentsEntry studentsEntry) {
		this.studentsEntry = studentsEntry;
	}

	public StudentsEntry getStudentsEntry() {
		return studentsEntry;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return studentsEntry.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return null;
	}

	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;
		if (!(obj instanceof StudentProfileEditorInput))
			return false;
		StudentProfileEditorInput other = (StudentProfileEditorInput) obj;
		return studentsEntry.getName().equals(other.getName());
	}

	public int hashCode() {
		return studentsEntry.getName().hashCode();
	}
}
