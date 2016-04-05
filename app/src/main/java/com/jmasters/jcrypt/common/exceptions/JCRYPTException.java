package com.jmasters.jcrypt.common.exceptions;

import java.util.ResourceBundle;

import com.jmasters.common.utils.XMLResourceBundle;
import com.jmasters.common.utils.XMLResourceBundleControl;
import com.jmasters.jcrypt.common.model.Message;

public class JCRYPTException extends Exception {

	public static final int MAX_INPUT_SIZE_IN_BYTES = 1024;

	public static final int MAX_INPUT_SIZE_IN_KILOBYTES = MAX_INPUT_SIZE_IN_BYTES / 1024;

	public static final String MAX_INPUT_SIZE_IN_KILOBYTES_STRING = String.valueOf(MAX_INPUT_SIZE_IN_KILOBYTES) + "KB";

	private static final long serialVersionUID = 8331959032726216073L;
	private long messageId;
	private String[] args;

	private static XMLResourceBundle resourceBundle = null;

	static {
		if (resourceBundle == null) {
			resourceBundle = (XMLResourceBundle) ResourceBundle.getBundle("JCRYPTMessages",
					new XMLResourceBundleControl());
		}
	}

	public JCRYPTException(long messageId, Throwable cause) {
		super(resourceBundle.getString(Long.toString(messageId), null), cause);
		this.messageId = messageId;
	}

	public JCRYPTException(long messageId) {
		super(resourceBundle.getString(Long.toString(messageId), null));
		this.messageId = messageId;
	}

	public JCRYPTException(long messageId, String[] args) {
		super(resourceBundle.getString(Long.toString(messageId), args));
		this.messageId = messageId;
		this.args = args;
	}

	public JCRYPTException(long messageId, String[] args, Throwable cause) {
		super(resourceBundle.getString(Long.toString(messageId), args), cause);
		this.messageId = messageId;
		this.args = args;
	}

	public JCRYPTException(Message message, Throwable cause) {
		super(resourceBundle.getString(Long.toString(message.getMessageId()), message.getArgs()), cause);
		this.messageId = message.getMessageId();
		this.args = message.getArgs();
	}

	public JCRYPTException(Message message) {
		super(resourceBundle.getString(Long.toString(message.getMessageId()), message.getArgs()));
		this.messageId = message.getMessageId();
		this.args = message.getArgs();
	}

	public long getMessageId() {
		return messageId;
	}

	public String[] getArgs() {
		return args;
	}

}