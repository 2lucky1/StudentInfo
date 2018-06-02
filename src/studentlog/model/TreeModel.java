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
		System.out.println(getLogFilePath());
		root = logFileAccessManager.readLogItemsFromFile(getLogFilePath());
		setRoot(root);
	}
}
