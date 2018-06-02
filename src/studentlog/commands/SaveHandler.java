package studentlog.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import studentlog.model.TreeModel;
import studentlog.services.LogFileAccessManager;

public class SaveHandler extends AbstractHandler {

	private static final String ID = "studentlog.commands.save";
	
	private IWorkbenchWindow window;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Save menu");
		
		window = HandlerUtil.getActiveWorkbenchWindow(event);

		FileDialog fd = new FileDialog(window.getShell(), SWT.SAVE);
        fd.setText("Save");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.json","*.txt", "*.doc", ".rtf", "*.*" };
        fd.setFilterExtensions(filterExt);
        String selectedFilePath = fd.open();
		
		LogFileAccessManager logFileAccessManager = new LogFileAccessManager();
		logFileAccessManager.writeLogItemsToFile(selectedFilePath, TreeModel.getInstance().getRoot());
		return null;
	}
}
