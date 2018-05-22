package studentlog.commands;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import studentlog.model.TreeModel;
import studentlog.services.LogFileAccessManager;


public class SaveHandler extends AbstractHandler {

//	private String fileName = new File("resources/log.json").getAbsolutePath();
//	String fileName = "studentlog.json";
//	String path = System.getProperty("user.dir") + fileName;
		
//	IWorkspace workspace = ResourcesPlugin.getWorkspace();
//	IWorkspaceRoot root = workspace.getRoot();
//	IProject project  = root.getProject("MyProject");
//	IFolder folder = project.getFolder("Folder1");
//	IFile file = folder.getFile("hello.txt");
	
//	 String path = new File("src/main/resources/conf.properties")
//             .getAbsolutePath();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Save menu");
//		File f = new File("test.txt");
//		System.out.println("second point" + f.getAbsolutePath());
		LogFileAccessManager logFileAccessManager = new LogFileAccessManager();
		logFileAccessManager.writeLogItemsToFile(TreeModel.getInstance().getLogFilePath(), TreeModel.getInstance().getRoot());
		return null;
	}

}
