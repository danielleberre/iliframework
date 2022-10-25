package fr.univartois.iliframework.logging;

import jakarta.servlet.jsp.tagext.TagData;
import jakarta.servlet.jsp.tagext.TagExtraInfo;
import jakarta.servlet.jsp.tagext.ValidationMessage;

/**
 * Check that the attribute of the LogTag tag
 * is correct.
 * 
 * It must correspond to one of the verbosity levels
 * found in the enumeration Level.
 * 
 * @author leberre
 * @see LogTag
 * @see Level
 */
public class LogTEI extends TagExtraInfo {
	@Override
	public ValidationMessage[] validate(TagData data) {
		Object o = data.getAttribute("level");
		ValidationMessage[] message = new ValidationMessage[0];
		if (o != null) {
			try {
				Level.valueOf(o.toString().toUpperCase());
			} catch (Exception e) {
				message = new ValidationMessage[1];
				message[0] = new ValidationMessage(data.getId(),
						"Invalid Log level value.");
			}
		}
		return message;
	}
}
