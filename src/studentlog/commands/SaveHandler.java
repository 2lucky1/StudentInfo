package studentlog.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import studentlog.model.TreeModel;
import studentlog.services.LogFileAccessManager;

public class SaveHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Save menu");
		LogFileAccessManager logFileAccessManager = new LogFileAccessManager();
		logFileAccessManager.writeLogItemsToFile(TreeModel.getInstance().getLogFilePath(), TreeModel.getInstance().getRoot());
		return null;
	}
}
