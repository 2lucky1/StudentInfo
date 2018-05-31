package studentlog.model;

import java.util.ArrayList;
import java.util.List;

import rcpapp.model.Node;
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
	
	public ITreeItem findNodeByPath(ITreeItem studentsEntry, String path) {
		boolean isPoint = (path.indexOf("/") == -1) ? true : false; //true-it's target
		String name=path.split("/")[0]; //name node 
		ITreeItem currentNode=(ITreeItem) studentsEntry.getChildren()
			.stream()
			.filter(c->c.getName().equals(name))
			.findFirst().orElse(null);
		return (isPoint)?currentNode:findNodeByPath(currentNode,path.substring(name.length()+1, path.length()));
		
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
		
		
//		
//		root = new Root();
//		Folder folder = new Folder(root, "Folder");
//
//		StudentsGroup firstGroup = new StudentsGroup(folder, "Group1");
//		StudentsGroup secondGroup = new StudentsGroup(folder, "Group2");
//
//		StudentsEntry vasya = new StudentsEntry("Вася", "1", "Красная,3", "Днепр", "5", firstGroup);
//		StudentsEntry petya = new StudentsEntry("Петя", "1", "Желтая,75", "Днепр", "3", firstGroup);
//		StudentsEntry vanya = new StudentsEntry("Ваня", "2", "Зеленая,36", "Днепр", "4", secondGroup);
//		StudentsEntry sasha = new StudentsEntry("Саша", "2", "Синяя,8", "Днепр", "2", secondGroup);
//
//		firstGroup.addEntry(vasya);
//		firstGroup.addEntry(petya);
//
//		secondGroup.addEntry(vanya);
//		secondGroup.addEntry(sasha);
//
//		folder.addEntry(firstGroup);
//		folder.addEntry(secondGroup);
//
//		root.addEntry(folder);
	}
}
