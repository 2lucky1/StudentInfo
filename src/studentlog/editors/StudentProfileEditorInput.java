package studentlog.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

import studentlog.model.StudentsEntry;

public class StudentProfileEditorInput implements IEditorInput {
	private final static String FACTORY_ID = "rcpapp.StudentProfileEditorInputFactory";
	public final static String FEATURE_ID = "featureId";
	private final StudentsEntry studentsEntry;
	private String partisipant;

	public StudentProfileEditorInput(StudentsEntry studentsEntry) {
		this.studentsEntry = studentsEntry;
	}

	public StudentsEntry getStudentsEntry() {
		return studentsEntry;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
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
		return new IPersistableElement() {
			public String getFactoryId() {
				return FACTORY_ID;
			}

			public void saveState(IMemento memento) {
				memento.putString(FEATURE_ID, studentsEntry.fullPath());
			}
		};
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
		return partisipant.equals(other.partisipant);
	}

	public int hashCode() {
		return partisipant.hashCode();
	}
}
