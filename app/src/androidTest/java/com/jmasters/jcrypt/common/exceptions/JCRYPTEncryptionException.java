package com.jmasters.jcrypt.common.exceptions;


import com.jmasters.jcrypt.common.model.Message;
import com.jmasters.jcrypt.common.model.MessagesConstants;

public class JCRYPTEncryptionException extends JCRYPTException {

	private static final long serialVersionUID = 1L;

	public JCRYPTEncryptionException(Throwable cause) {
		super(MessagesConstants.ERROR_ENCRYPTION_FAILURE, cause);
	}

	public JCRYPTEncryptionException(Message message) {
		super(message);
	}

	public JCRYPTEncryptionException(long messageId, String[] args) {
		super(messageId, args);
	}

	public JCRYPTEncryptionException(long messageId) {
		super(messageId);
	}

}
