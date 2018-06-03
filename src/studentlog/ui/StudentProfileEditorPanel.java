package studentlog.ui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
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
		
		// TODO Auto-generated constructor stub
	}

	private void createContent(Composite parent) {
//		Color bgc = this.getDisplay().getSystemColor(SWT.COLOR_WHITE);
//		setBackground(bgc);
//
//		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
//		setLayoutData(gridData);
		GridData gridData1 = new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false);
		
		
//		setLayoutData(gridData);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.horizontalSpacing = 10;
		setLayout(gridLayout);
		
//		gridLayout.verticalSpacing = 10;
//		gridLayout.horizontalSpacing = 40;
//		gridLayout.marginLeft = 12;
//		gridLayout.marginTop = 10;

		
//		setLayout(gridLayout);
//		setBackground(parent.getShell().getBackground());

		labelName = new Label(this, SWT.NONE);
		labelName.setText("Name");
//		gridData = new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false);
		gridData1.horizontalIndent = 10;
		labelName.setLayoutData(gridData1);

		textName = new Text(this, SWT.BORDER);
		textName.setToolTipText("Student name");
		gridData1 = new GridData();
		gridData1.widthHint = 150;
		gridData1.heightHint = 15;
//		gridData1.horizontalIndent =;
		textName.setLayoutData(gridData1);
		
		
	    Canvas studentPhoto = new Canvas(this, SWT.BORDER);
	    GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, true, false);
	    gridData.widthHint = 255;
	    gridData.heightHint = 255; 
	    gridData.verticalSpan = 6;
	    gridData.horizontalIndent = 20;
	    studentPhoto.setLayoutData(gridData);
	    Image image = new Image(parent.getDisplay(), ProjectPathFinder.getStudentsPictures() + "imagenotfound.png" );
	    		studentPhoto.addPaintListener(new PaintListener() {
	    	@Override
	    	public void paintControl(final PaintEvent event) {
	    		if (image != null) {
	    			event.gc.drawImage(image, 0, 0);
	    		}
	    	}
	    });
	    
		labelGroup = new Label(this, SWT.NONE);
//		gridData1 = new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false);
//		gridData1.horizontalIndent = 10;
		labelGroup.setText("Group");
		gridData1.horizontalIndent = 10;
		labelGroup.setLayoutData(gridData1);

		textGroup = new Text(this, SWT.BORDER);
		textGroup.setToolTipText("Student group");
		gridData1 = new GridData();
		gridData1.widthHint = 65;
		gridData1.heightHint = 15;
		gridData1.horizontalIndent = -100;
		textGroup.setLayoutData(gridData1);

		labelAddress = new Label(this, SWT.NONE);
		labelAddress.setText("Address");
		gridData1.horizontalIndent = 10;
		labelAddress.setLayoutData(gridData1);

		textAddress = new Text(this, SWT.BORDER);
		textAddress.setToolTipText("Address");
		gridData1 = new GridData();
		gridData1.widthHint = 60;
		gridData1.heightHint = 15;
//		gridData.horizontalIndent = -50;
		textAddress.setLayoutData(gridData1);

		labelCity = new Label(this, SWT.NONE);
		labelCity.setText("City");
		gridData1.horizontalIndent = 10;
		labelCity.setLayoutData(gridData1);

		textCity = new Text(this, SWT.BORDER);
		textCity.setToolTipText("City");
		gridData1 = new GridData();
		gridData1.widthHint = 100;
		gridData1.heightHint = 15;
//		gridData.horizontalIndent = ;
		textCity.setLayoutData(gridData1);

		labelResult = new Label(this, SWT.NONE);
		labelResult.setText("Result");
		gridData1.horizontalIndent = 10;
		labelResult.setLayoutData(gridData1);

		textResult = new Text(this, SWT.BORDER);
		textResult.setToolTipText("Result");
		gridData1 = new GridData();
		gridData1.widthHint = 70;
		gridData1.heightHint = 15;
//		gridData.horizontalIndent = -50;
		textResult.setLayoutData(gridData1);

		

	}
	
	public void fillPanelArea(StudentsEntry entry) {
		textName.setText(entry.getName());
		textGroup.setText(entry.getGroupNumber());
		textAddress.setText(entry.getAddress());
		textCity.setText(entry.getCity());
		textResult.setText(entry.getResult());
	}

	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		StudentProfileEditorPanel panel = new StudentProfileEditorPanel(shell, SWT.NONE);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
