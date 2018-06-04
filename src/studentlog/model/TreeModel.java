package studentlog.model;

import java.util.ArrayList;
import java.util.List;

import studentlog.services.LogFileAccessManager;
import studentlog.services.ProjectPathFinder;

public class TreeModel implements Observable {

	private static final String DEFAULT_FILE_NAME = "studentlog.json";

	private List<Observer> observers = new ArrayList<Observer>();

	private Root root;
	private LogFileAccessManager logFileAccessManager;
	private static TreeModel instance;

	private TreeModel() {
		super();
		initTreeModel();
	}

	public static TreeModel getInstance() {
		if (instance == null) {
			instance = new TreeModel();
		}
		return instance;
	}

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers(List<Observer> observers) {
		for (Observer observer : observers) {
			observer.update(root);
		}
	}
	
	public ITreeItem findNodeByFullPath(String fullPath) {
		return findNodeByPath(getRoot(), fullPath.substring(1, fullPath.length()));
	}

	public ITreeItem findNodeByPath(ITreeItem studentsEntry, String path) {
		List<Folder> folders = (List<Folder>) studentsEntry.getChildren(); 
		for(Folder folder : folders) {
			List<StudentsGroup> groups = folder.getChildren();
			for(StudentsGroup group : groups) {
				List<StudentsEntry> entries = group.getChildren();
				for(StudentsEntry entry : entries) {
					String tempPath = folder.getName() + "/" + group.getName() + "/" + entry.getName();
					if (tempPath.equals(path)) {
						return entry;
					}
				}
			}
		}
		return null;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
		notifyObservers(observers);
	}

	public String getLogFilePath() {
	return ProjectPathFinder.getJSONFolderPath() + DEFAULT_FILE_NAME;
	}

	private void initTreeModel() {
		logFileAccessManager = new LogFileAccessManager();
		root = logFileAccessManager.readLogItemsFromFile(getLogFilePath());
		setRoot(root);
	}
}
