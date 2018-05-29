package studentlog.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import studentlog.model.Root;
import studentlog.model.TreeModel;
import studentlog.services.LogFileAccessManager;

public class OpenHandler extends AbstractHandler {

	private IWorkbenchWindow window;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindow(event);
		
		FileDialog fd = new FileDialog(window.getShell(), SWT.OPEN);
		fd.setText("Open");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.json","*.txt", "*.doc", ".rtf", "*.*" };
        fd.setFilterExtensions(filterExt);
        String selectedFilePath = fd.open();
        if(selectedFilePath!=null){
        LogFileAccessManager logFileAccessManager = new LogFileAccessManager();
		Root root = logFileAccessManager.readLogItemsFromFile(selectedFilePath);
		TreeModel.getInstance().setRoot(root);
        }
		return null;
	}

}
