/**
 * 
 */
package com.jmasters.jcrypt.common.model;

import java.util.ResourceBundle;

import com.jmasters.common.utils.XMLResourceBundle;
import com.jmasters.common.utils.XMLResourceBundleControl;

/**
 * @author alexb
 * 
 */
public class Message {

	public enum TYPE {
		INFO, WARNING, ERROR
	};

	private long messageId;
	private String[] args;
	private TYPE type = TYPE.INFO;

	private static XMLResourceBundle resourceBundle = null;

	static {
		if (resourceBundle == null) {
			resourceBundle = (XMLResourceBundle) ResourceBundle.getBundle("JCRYPTMessages",
					new XMLResourceBundleControl());
		}
	}

	public Message(long messageId, String[] args, TYPE type) {
		this.messageId = messageId;
		this.args = args;
		this.type = type;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getMessage() {
		return resourceBundle.getString(Long.toString(this.messageId), this.args);
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

}
