package com.jmasters.jcrypt.common.exceptions;


import com.jmasters.jcrypt.common.model.Message;
import com.jmasters.jcrypt.common.model.MessagesConstants;

public class JCRYPTDecryptionException extends JCRYPTException {

	private static final long serialVersionUID = 1L;

	public JCRYPTDecryptionException(Throwable cause) {
		super(MessagesConstants.ERROR_DECRYPTION_FAILURE, cause);
	}

	public JCRYPTDecryptionException(long messageId) {
		super(messageId);
	}

	public JCRYPTDecryptionException(long messageId, String[] args) {
		super(messageId, args);
	}

	public JCRYPTDecryptionException(Message message) {
		super(message);
	}
}
