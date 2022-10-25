package fr.univartois.iliframework.listeners;

import java.util.Locale;
import java.util.ResourceBundle;

import fr.univartois.iliframework.Enumerable;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application context listener to load parameters from a properties file
 * instead of the web.xml file.
 * 
 * By default, the file <code>parameters.properties</code> is expected to contain the application parameters.
 * 
 * The name of the file can be configured using the <code>parametersFile</code> parameter.
 * 
 * @author leberre
 */
@WebListener
public class EasyApplicationParameters implements ServletContextListener {

	/**
	 * Look for application parameters in a properties file.
	 * 
	 * The key/value pairs are loaded from the properties file to the ServletContext object.
	 */
    @Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().log("Easy Application Parameter filter enabled");
		String parametersFile = sce.getServletContext().getInitParameter("parametersFile");
		if (parametersFile == null) {
			parametersFile = "parameters";
		}
		ResourceBundle parameters = ResourceBundle.getBundle(parametersFile, Locale.getDefault());
	
		for (String s : Enumerable.of(parameters.getKeys())) {
			sce.getServletContext().setAttribute(s, parameters.getString(s));
		}
	}
	
    @Override
	public void contextDestroyed(ServletContextEvent sce) {
		// nothing to do when the application is stopped
	}
}
