package studentlog.model;

import java.util.List;

public interface ITreeItem {
	
	public List<?> getChildren();
	public boolean hasChildren();
	public String getName();
	public ITreeItem getParent();
//	public boolean isStudentsEntry();

}
