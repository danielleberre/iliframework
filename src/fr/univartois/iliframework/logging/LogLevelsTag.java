package fr.univartois.iliframework.logging;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag to iterate over the verbosity levels.
 * 
 * Used to display a verbosity level selector in a JSP page.
 * 
 * @author leberre
 *
 */
public class LogLevelsTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int index;
	private String var;
	
	public void setVar(String var) {
		this.var = var;
	}
	
	private int updateContext(int returnCode) {
		if (index < Level.values().length) {
			pageContext.setAttribute(var, Level.values()[index++]);
			return returnCode;
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doStartTag() throws JspException {
		index = 0;
		return updateContext(EVAL_BODY_INCLUDE);
	}

	@Override
	public int doAfterBody() throws JspException {
		return updateContext(EVAL_BODY_AGAIN);
	}

	@Override
	public int doEndTag() throws JspException {
		pageContext.removeAttribute(var);
		return super.doEndTag();
	}
}
