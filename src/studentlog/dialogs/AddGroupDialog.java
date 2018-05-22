package studentlog.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import studentlog.services.InputVerifier;

public class AddGroupDialog extends Dialog{
	private Text groupNumberText;
	private Label groupNumberLabel;
	private String groupNumber;

	public AddGroupDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Add Group");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		groupNumberLabel = new Label(composite, SWT.NONE);
		groupNumberLabel.setText("&Group name:"); 
		groupNumberLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		groupNumberText = new Text(composite, SWT.BORDER);
		groupNumberText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		return composite;
	}

	@Override
	protected void okPressed() {
		groupNumber = groupNumberText.getText();
		
		if(!InputVerifier.verifyGroupNumber(groupNumber)) {
			MessageDialog.openError(getShell(), "Invalid group number", 
					"Group field must contains an integer number."); 
			return;
		}
		super.okPressed();
	}

	public String getGroupNumber() {
		return groupNumber;
	}	
}
