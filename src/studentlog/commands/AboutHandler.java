package studentlog.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;


public class AboutHandler extends AbstractHandler {

	private static final String ID = "studentlog.commands.about";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		String message = "This program allows you to keep a log of homework."
				+ " Provides the ability to add, delete students entry,"
				+ " drug it from the tree and drop to the editor area ."
				+ " And also it is possible to save the edited log to a file.";
		
		MessageDialog dialog = new MessageDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "About the program", null,
			    message, MessageDialog.INFORMATION, new String[] { "About"}, 0);
			dialog.open();
		return null;
	}
}
