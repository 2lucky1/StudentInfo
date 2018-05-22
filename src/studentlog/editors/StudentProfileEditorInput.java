package studentlog.editors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class StudentProfileEditorInput implements IEditorInput{

	private String partisipant;
	
	public StudentProfileEditorInput(String partisipant) {
		super();
		Assert.isNotNull(partisipant);
		this.partisipant = partisipant;
	}
	
	public StudentProfileEditorInput() {
		
	}
	
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return partisipant;
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
		if (super.equals(obj)) return true;
		if (!(obj instanceof StudentProfileEditorInput))
		return false;
		StudentProfileEditorInput other = (StudentProfileEditorInput) obj;
		return partisipant.equals(other.partisipant);
		}
		public int hashCode() {
		return partisipant.hashCode();
		}
}
