package fr.univartois.iliframework.logging;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

/**
 * Tag that display it's content depending of the verbosity
 * level of the webapp.
 * 
 * <code>
 * <ili:log level="info">
 * <p>This is a message of level ${level}.</p>
 * <p>The verbosity level of the webapp is ${applicationLevel}.</p>
 * </ili:log>
 * </code>
 * 
 * @author leberre
 *
 */
public class LogTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Level level;

	public void setLevel(String st) {
		level = Level.valueOf(st.toUpperCase());
	}

	boolean needClosingDiv;

	@Override
	public int doStartTag() throws JspException {
		Level appliLevel = (Level) pageContext.getServletContext()
				.getAttribute("applicationLevel");
		if (level.compareTo(appliLevel) >= 0) {
			try {
				pageContext.getOut().newLine();
				pageContext.getOut().print(
						"<div class=\"" + level.toString().toLowerCase()
								+ "\">");
				pageContext.getOut().newLine();
				pageContext.setAttribute("level", level);
				needClosingDiv = true;
				return EVAL_BODY_INCLUDE;
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		needClosingDiv = false;
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		if (needClosingDiv) {
			try {
				pageContext.getOut().newLine();
				pageContext.getOut().print("</div>");
				pageContext.getOut().newLine();
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		return super.doEndTag();
	}

}