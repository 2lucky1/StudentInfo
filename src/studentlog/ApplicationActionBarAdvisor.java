package studentlog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private StatusLineContributionItem statusItem;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void fillStatusLine(IStatusLineManager statusLine) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		statusItem = new StatusLineContributionItem("CurrentDateInStatus");
		statusItem.setText("Date: " + sdf.format(date));
		statusLine.add(statusItem);
	}
}
