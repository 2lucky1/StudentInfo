package studentlog.ui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import studentlog.model.StudentsEntry;

public class StudentProfileEditorPanel extends Composite {

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
	private Label labelImage;
	private String imagePath;

	public StudentProfileEditorPanel(Composite parent, int style) {
		super(parent, style);
		createContent(parent);
		
		// TODO Auto-generated constructor stub
	}

	private void createContent(Composite parent) {
		Color bgc = this.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		setBackground(bgc);

		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
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
		gridData.horizontalIndent = -37;
		textName.setLayoutData(gridData);

		labelGroup = new Label(this, SWT.NONE);
		labelGroup.setText("Group");

		textGroup = new Text(this, SWT.BORDER);
		textGroup.setToolTipText("Student group");
		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -37;
		textGroup.setLayoutData(gridData);

		labelAddress = new Label(this, SWT.NONE);
		labelAddress.setText("Address");

		textAddress = new Text(this, SWT.BORDER);
		textAddress.setToolTipText("Address");
		gridData = new GridData();
		gridData.widthHint = 200;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -37;
		textAddress.setLayoutData(gridData);

		labelCity = new Label(this, SWT.NONE);
		labelCity.setText("City");

		textCity = new Text(this, SWT.BORDER);
		textCity.setToolTipText("City");
		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -37;
		textCity.setLayoutData(gridData);

		labelResult = new Label(this, SWT.NONE);
		labelResult.setText("Result");

		textResult = new Text(this, SWT.BORDER);
		textResult.setToolTipText("Result");
		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -37;
		textResult.setLayoutData(gridData);

//		labelImage = new Label(this, SWT.NONE);
//		if (imagePath == null) {
//			labelImage.setImage(new Image(this.getDisplay(), ProjectPathFinder.getStudentsPictures() + "imagenotfound.png"));
//		}
//		labelImage.setImage(new Image(this.getDisplay(), imagePath));
//		gridData = new GridData();
//		gridData.horizontalIndent = 50;
//		gridData.verticalIndent = 10;
//		labelImage.setLayoutData(gridData);

	}
	
	public void fillPanelArea(StudentsEntry entry) {
		textName.setText(entry.getName());
		textGroup.setText(entry.getGroupNumber());
		textAddress.setText(entry.getAddress());
		textCity.setText(entry.getCity());
		textResult.setText(entry.getResult());
	}

}
