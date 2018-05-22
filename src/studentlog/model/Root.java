package studentlog.model;

import java.util.ArrayList;
import java.util.List;

public class Root implements ITreeItem {
	
	private String name = "Root";
	transient ITreeItem parent = null;
	private List<Folder> folders = new ArrayList<Folder>();

	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public ITreeItem getParent() {
		return parent;
	}
	
	@Override
	public List<Folder> getChildren() {
		return folders;
	}

	@Override
	public boolean hasChildren() {
		return folders.size() > 0;
	}
	
	public void addStudentsListener(IStudentsListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void removeStudentsListener(IStudentsListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void fireStudentsChanged(StudentsEntry entry) {
		// TODO Auto-generated method stub
		
	}

	public void addEntry(Folder folder) {
		folders.add(folder);
	}

	public void initParent() {
		for (Folder folder : folders) {
			folder.setParent(this);
			folder.initParent();
		}
	}
}
