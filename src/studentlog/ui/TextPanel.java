package studentlog.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import studentlog.model.StudentsEntry;

public class TextPanel extends Composite {

	private Label labelName;
	private Text textName;
	private Label labelGroup;
	private Text textGroup;
	private Label labelAddress;
	private Text textAddress;
	private Label labelCity;
	private Text textCity;
	private Label labelResult;
	private Text textResult;
	
	public TextPanel(Composite parent, int style) {
		super(parent, style);
		createContent(parent);
	}
	
	
	
	public String getTextName() {
		return textName.getText();
	}



	public void setTextName(String textName) {
		this.textName.setText(textName);
	}



	public String getTextGroup() {
		return textGroup.getText();
	}



	public void setTextGroup(String textGroup) {
		this.textGroup.setText(textGroup);
	}



	public String getTextAddress() {
		return textAddress.getText();
	}



	public void setTextAddress(String textAddress) {
		this.textAddress.setText(textAddress);
	}



	public String getTextCity() {
		return textCity.getText();
	}



	public void setTextCity(String textCity) {
		this.textCity.setText(textCity);
	}



	public String getTextResult() {
		return textResult.getText();
	}



	public void setTextResult(String textResult) {
		this.textResult.setText(textResult);
	}



	private void createContent(Composite parent){
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		setLayoutData(gridData);
		
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 40;
		gridLayout.marginLeft = 12;
		gridLayout.marginTop = 10;

		setLayout(gridLayout);
		setBackground(parent.getShell().getBackground());

		labelName = new Label(this, SWT.NONE);
		labelName.setText("Name");

		textName = new Text(this, SWT.BORDER);
		textName.setToolTipText("Student name");
		gridData = new GridData();
		gridData.widthHint = 150;
		gridData.heightHint = 15;
//		gridData.horizontalIndent = -37;
		textName.setLayoutData(gridData);

		labelGroup = new Label(this, SWT.NONE);
		labelGroup.setText("Group");

		textGroup = new Text(this, SWT.BORDER);
		textGroup.setToolTipText("Student group");
		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.heightHint = 15;
//		gridData.horizontalIndent = -37;
		textGroup.setLayoutData(gridData);

		labelAddress = new Label(this, SWT.NONE);
		labelAddress.setText("Address");

		textAddress = new Text(this, SWT.BORDER);
		textAddress.setToolTipText("Address");
		gridData = new GridData();
		gridData.widthHint = 200;
		gridData.heightHint = 15;
//		gridData.horizontalIndent = -37;
		textAddress.setLayoutData(gridData);

		labelCity = new Label(this, SWT.NONE);
		labelCity.setText("City");

		textCity = new Text(this, SWT.BORDER);
		textCity.setToolTipText("City");
		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.heightHint = 15;
//		gridData.horizontalIndent = -37;
		textCity.setLayoutData(gridData);

		labelResult = new Label(this, SWT.NONE);
		labelResult.setText("Result");

		textResult = new Text(this, SWT.BORDER);
		textResult.setToolTipText("Result");
		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.heightHint = 15;
//		gridData.horizontalIndent = -37;
		textResult.setLayoutData(gridData);
	}
	
	public void fillPanel(StudentsEntry entry) {
		textName.setText(entry.getName());
		textGroup.setText(entry.getGroupNumber());
		textAddress.setText(entry.getAddress());
		textCity.setText(entry.getCity());
		textResult.setText(entry.getResult());
	}

}
