package studentlog;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.part.EditorInputTransfer;

import studentlog.dnd.MyDropListener;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(850, 400));
		configurer.setShowCoolBar(true);
		configurer.setShowPerspectiveBar(true);
		configurer.setShowStatusLine(true);
		configurer.setTitle("StudentLog");

		configurer.addEditorAreaTransfer(EditorInputTransfer.getInstance());
		configurer.configureEditorAreaDropListener(new MyDropListener(configurer.getWindow()));
	}
}
