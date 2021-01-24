package studentlog.services;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class ProjectPathFinder {

	public static String getProjectAbsolutePath() {
		Bundle bundle = FrameworkUtil.getBundle(ProjectPathFinder.class);
		URL projectURL = bundle.getEntry(""); 

		String projectAbsolutePath = null;
		try {
			
//			filePath = Platform.resolve(pluginInternalURL).getFile(); pre 3.2 eclipse version
			
			projectAbsolutePath = FileLocator.resolve(projectURL).getFile(); //after 3.2 eclipse version
			if (projectAbsolutePath.charAt(0) == '\\') { 
				projectAbsolutePath = projectAbsolutePath.substring(1); 
			} 
			return projectAbsolutePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectAbsolutePath;
	}
	
	public static String getResourcesFolderPath() {
		return getProjectAbsolutePath() + "resources/";
	}
	
	public static String getIconsFolderPath() {
		return getResourcesFolderPath() + "icons/";
	}
	
	public static String getJSONFolderPath() {
		return getResourcesFolderPath() + "json/";
	}
	
	public static String getStudentsPictures() {
		return getResourcesFolderPath() + "students_pictures/";
	}
	
}
