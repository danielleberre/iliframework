package fr.univartois.iliframework.logging;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet controller to take care of changing the
 * verbosity level of the webapp.
 * 
 * The class also initializes the verbosity from a 
 * specific <code>loglevel</code> servlet parameter.
 * 
 * The verbosity level is stored in the application scope
 * as <code>applicationLevel</code>.
 * 
 * @author leberre
 *
 */
@WebServlet("/LogController")
@WebListener
public class LogController extends HttpServlet implements ServletContextListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		changeLevel(getServletContext(), arg0.getParameter("loglevel"));
		String referer = arg0.getHeader("referer");
		if (referer != null) {
			arg1.sendRedirect(referer);
		}
	}

	private void changeLevel(ServletContext context, String initLevel) {
		context.setAttribute("applicationLevel",
				initLevel == null ? Level.INFO : Level.valueOf(initLevel.toUpperCase()));
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doGet(arg0, arg1);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String initLevel = sce.getServletContext().getInitParameter("loglevel");
		sce.getServletContext().log("initial log level =>" + initLevel);
		changeLevel(sce.getServletContext(), initLevel);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// nothing to do when the application is stopped
	}
}
