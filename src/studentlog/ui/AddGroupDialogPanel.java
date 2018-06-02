package studentlog.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddGroupDialogPanel extends Composite {

	private Text groupNumberText;
	private Label groupNumberLabel;
	
	public AddGroupDialogPanel(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);

		groupNumberLabel = new Label(this, SWT.NONE);
		groupNumberLabel.setText("&Group name:"); 
		groupNumberLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		groupNumberText = new Text(this, SWT.BORDER);
		groupNumberText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
	}
	
	public String getGroupNumber() {
		return groupNumberText.getText();
	}
}
