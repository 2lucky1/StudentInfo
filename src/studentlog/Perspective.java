package studentlog;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import studentlog.views.StudentsView;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.addStandaloneView(StudentsView.ID, false, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		layout.setFixed(true);
	}
}
