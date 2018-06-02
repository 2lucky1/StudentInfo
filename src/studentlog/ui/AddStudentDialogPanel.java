package studentlog.ui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddStudentDialogPanel extends Composite {

	private Text nameText;
	private Text addressText;
	private Text cityText;
	private Text resultText;
	
	public AddStudentDialogPanel(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);

		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText("&Name:"); 
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		nameText = new Text(this, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		Label adressLabel = new Label(this, SWT.NONE);
		adressLabel.setText("&Address:"); 
		adressLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		addressText = new Text(this, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, false);
		gridData.widthHint = 200;
		addressText.setLayoutData(gridData);
		
		Label cityLabel = new Label(this, SWT.NONE);
		cityLabel.setText("&City:"); 
		cityLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		cityText = new Text(this, SWT.BORDER);
		cityText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		
		Label resultLabel = new Label(this, SWT.NONE);
		resultLabel.setText("&Result:");
		resultLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, true, false));

		resultText = new Text(this, SWT.BORDER);
		resultText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
	}

	public String getStudentName() {
		return nameText.getText();
	}

	public String getAddress() {
		return addressText.getText();
	}

	public String getCity() {
		return cityText.getText();
	}

	public String getResult() {
		return resultText.getText();
	}

}
