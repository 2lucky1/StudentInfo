package studentlog.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;

public class StudentsGroup implements ITreeItem {

	private String name;

	transient private Folder parent;

	private List<StudentsEntry> children = new ArrayList<StudentsEntry>();

	transient private ListenerList listeners;


	public StudentsGroup() {
	};

	public StudentsGroup(Folder parent, String name) {
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
	public boolean hasChildren() {
		return children.size() > 0;
	}

	@Override
	public List<StudentsEntry> getChildren() {
		return children;
	}

	public ListenerList getListeners() {
		return listeners;
	}

	public void setListeners(ListenerList listeners) {
		this.listeners = listeners;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Folder folder) {
		this.parent = folder;
	}

	public void setChildren(List<StudentsEntry> children) {
		this.children = children;
	}

	public void rename(String newName) {
		this.name = newName;
		fireStudentsChanged(null);
	}

	public void addEntry(StudentsEntry childe) {
		children.add(childe);
		fireStudentsChanged(null);
	}

	public void removeEntry(StudentsEntry childe) {
		children.remove(childe);
		if (children.isEmpty()) {
			children = null;
		}
		fireStudentsChanged(null);
	}

	public void addStudentsListener(IStudentsListener listener) {
		if (parent != null) {
			parent.addStudentsListener(listener);
		} else {
			if (listeners == null)
				listeners = new ListenerList();
			listeners.add(listener);
		}
	}

	public void removeStudentsListener(IStudentsListener listener) {
		if (parent != null) {
			parent.removeStudentsListener(listener);
		} else {
			if (listeners != null) {
				listeners.remove(listener);
				if (listeners.isEmpty())
					listeners = null;
			}
		}
	}

	protected void fireStudentsChanged(StudentsEntry entry) {
		if (parent != null) {
			parent.fireStudentsChanged(entry);
		} else {
			if (listeners == null) {
				return;
			}
			Object[] rls = listeners.getListeners();
			for (int i = 0; i < rls.length; i++) {
				IStudentsListener listener = (IStudentsListener) rls[i];
				listener.studentsChanged(this, entry);
			}
		}
	}

	public void initParent() {
		for (StudentsEntry entry : children) {
			entry.setParent(this);
		}
	}
}
