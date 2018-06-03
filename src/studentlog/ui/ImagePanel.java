package studentlog.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import studentlog.model.StudentsEntry;
import studentlog.services.ProjectPathFinder;

public class ImagePanel extends Composite {

	private String imagePath;
	private Image image;
	
	public ImagePanel(Composite parent, int style) {
		super(parent, style);
		createContent(parent);
	}

	private void createContent(Composite parent){
//		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
//		setLayoutData(gridData);
		GridLayout gridLayout = new GridLayout();
		setLayout(gridLayout);
		
		Canvas studentPhoto = new Canvas(this, SWT.BORDER);
	    GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, true, false);
	    gridData.widthHint = 255;
	    gridData.heightHint = 255; 
	    gridData.verticalSpan = 6;
	    gridData.horizontalIndent = 20;
	    studentPhoto.setLayoutData(gridData);
	    image = new Image(parent.getDisplay(), ProjectPathFinder.getStudentsPictures() + "imagenotfound.png" );
	    		studentPhoto.addPaintListener(new PaintListener() {
	    	@Override
	    	public void paintControl(final PaintEvent event) {
	    		if (image != null) {
	    			event.gc.drawImage(image, 0, 0);
	    		}
	    	}
	    });
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void fillPanel(StudentsEntry entry) {
		Image newImage = new Image(this.getDisplay(),entry.getImagePath());
		setImage(newImage);
	}
	
}
