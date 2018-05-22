package studentlog.model;

import java.util.ArrayList;
import java.util.List;

public class Folder implements ITreeItem {
	
	private String name;
	transient private Root parent;
	
	private List<StudentsGroup> groups = new ArrayList<StudentsGroup>();

	public Folder(Root parent, String name) {
		super();
		this.parent = parent;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public ITreeItem getParent() {
		return parent;
	}

	@Override
	public List<StudentsGroup> getChildren() {
		return groups;
	}

	@Override
	public boolean hasChildren() {
		return groups.size() > 0;
	}

	public void addEntry(StudentsGroup group) {
		groups.add(group);
	}

	
	public void setParent(Root root) {
		this.parent = root;
	}

	public void initParent() {
		for (StudentsGroup group : groups) {
			group.setParent(this);
			group.initParent();
		}
	}

	public void removeStudentsListener(IStudentsListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void addStudentsListener(IStudentsListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void fireStudentsChanged(StudentsEntry entry) {
		// TODO Auto-generated method stub
		
	}
}
