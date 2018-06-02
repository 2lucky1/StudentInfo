package studentlog.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import studentlog.services.InputVerifier;
import studentlog.ui.AddGroupDialogPanel;

public class AddGroupDialog extends Dialog{

	AddGroupDialogPanel panel;
	private String groupNumber;
	public AddGroupDialog(Shell parentShell) {
		super(parentShell);
	}

	public String getGroupNumber() {
		return groupNumber;
	}	

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Add Group");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		panel = new AddGroupDialogPanel(parent, SWT.NONE);
		return panel;
	}

	@Override
	protected void okPressed() {
		groupNumber = panel.getGroupNumber();
		if(!InputVerifier.verifyGroupNumber(groupNumber)) {
			MessageDialog.openError(getShell(), "Invalid group number", 
					"Group field must contains an integer number."); 
			return;
		}
		super.okPressed();
	}

}
