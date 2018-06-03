package studentlog.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import studentlog.model.StudentsEntry;
import studentlog.services.ProjectPathFinder;

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
	}

	private void createContent(Composite parent) {
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		setLayoutData(gridData);
		
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.verticalSpacing = 20;
		gridLayout.horizontalSpacing = 40;
		gridLayout.marginLeft = 40;
		gridLayout.marginTop = 10;

		setLayout(gridLayout);
		setBackground(parent.getShell().getBackground());

		labelName = new Label(this, SWT.NONE);
		labelName.setText("Name");
		gridData = new GridData();
		labelName.setLayoutData(gridData);

		textName = new Text(this, SWT.BORDER);
		textName.setToolTipText("Student name");
		gridData = new GridData();
		gridData.widthHint = 150;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -15;
		textName.setLayoutData(gridData);
		
		labelImage = new Label(this, SWT.BORDER);
		if (imagePath == null) {
			labelImage.setImage(new Image(this.getDisplay(), ProjectPathFinder.getStudentsPictures() + "default1.png"));
		}else {
			labelImage.setImage(new Image(this.getDisplay(), imagePath));
		}
		gridData = new GridData();
		gridData.verticalSpan = 6;
		gridData.widthHint = 200;
		gridData.heightHint = 200;
		gridData.horizontalIndent = 70;
		labelImage.setLayoutData(gridData);

		labelGroup = new Label(this, SWT.NONE);
		labelGroup.setText("Group");

		textGroup = new Text(this, SWT.BORDER);
		textGroup.setToolTipText("Student group");
		gridData = new GridData();
		gridData.widthHint = 150;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -15;
		textGroup.setLayoutData(gridData);

		labelAddress = new Label(this, SWT.NONE);
		labelAddress.setText("Address");

		textAddress = new Text(this, SWT.BORDER);
		textAddress.setToolTipText("Address");
		gridData = new GridData();
		gridData.widthHint = 150;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -15;
		textAddress.setLayoutData(gridData);

		labelCity = new Label(this, SWT.NONE);
		labelCity.setText("City");

		textCity = new Text(this, SWT.BORDER);
		textCity.setToolTipText("City");
		gridData = new GridData();
		gridData.widthHint = 150;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -15;
		textCity.setLayoutData(gridData);

		labelResult = new Label(this, SWT.NONE);
		labelResult.setText("Result");

		textResult = new Text(this, SWT.BORDER);
		textResult.setToolTipText("Result");
		gridData = new GridData();
		gridData.widthHint = 150;
		gridData.heightHint = 15;
		gridData.horizontalIndent = -15;
		textResult.setLayoutData(gridData);
		
		

	}

	public void fillPanelArea(StudentsEntry entry) {
		textName.setText(entry.getName());
		textGroup.setText(entry.getGroupNumber());
		textAddress.setText(entry.getAddress());
		textCity.setText(entry.getCity());
		textResult.setText(entry.getResult());
	}
}
