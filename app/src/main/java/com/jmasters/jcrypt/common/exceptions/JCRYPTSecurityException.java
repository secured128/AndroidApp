package com.jmasters.jcrypt.common.exceptions;


import com.jmasters.jcrypt.common.model.MessagesConstants;

public class JCRYPTSecurityException extends JCRYPTDecryptionException {

	private static final long serialVersionUID = 1L;

	public JCRYPTSecurityException() {
		super(MessagesConstants.ERROR_SECURITY_AUTHORIZATION_VIOLATION);
	}

}
