package studentlog.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import studentlog.services.InputVerifier;
import studentlog.ui.AddStudentDialogPanel;

public class AddStudentDialog extends Dialog {
	private String name;
	private String address;
	private String city;
	private String result;
	AddStudentDialogPanel panel;

	public AddStudentDialog(Shell parentShell) {
		super(parentShell);
	}

	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getResult() {
		return result;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Add Student"); 
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		panel = new AddStudentDialogPanel(parent, SWT.NONE);
		return panel;
	}

	@Override
	protected void okPressed() {
		name = panel.getStudentName();
		address = panel.getAddress();
		city = panel.getCity();
		result = panel.getResult();
		if(!verifiesInput(name, address, city, result)) {
			return;
		}
		super.okPressed();
	}
	
	private boolean verifiesInput(String name, String address, String city, String result) {
		if(!InputVerifier.verifyName(name)) {
			MessageDialog.openError(getShell(), "Invalid name", 
					"Name field must contains first and last name, "
					+ "begins with capital letter, must not contains digits"); 
			return false;
		}
		
		if(!InputVerifier.verifyAddress(address)) {
			MessageDialog.openError(getShell(), "Invalid address", 
					"Address field must contain street name, comma and address number,"
					+ " street name must begins with capital letter."); 
			return false;
		}
		
		if(!InputVerifier.verifyCity(city)) {
			MessageDialog.openError(getShell(), "Invalid city name", 
					"City field must contains a city name, which begins with a capital letter"); 
			return false;
		}
		
		if(!InputVerifier.verifyResultByFivePointSystem(result)) {
			MessageDialog.openError(getShell(), "Invalid result", 
					"Result field must contains a mark from 1 to 5 point, only integers are allowed."); 
			return false;
		}
		return true;
	}
}
