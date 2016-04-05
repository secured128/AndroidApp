/**
 * 
 */
package com.jmasters.jcrypt.common.exceptions;

import com.jmasters.jcrypt.common.model.Message;

/**
 * @author alexb
 * 
 */
public class UserManagerException extends Exception {

	private static final long serialVersionUID = 1L;
	private Message errorMessage = null;

	public UserManagerException(Message errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Message getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Message errorMessage) {
		this.errorMessage = errorMessage;
	}

}
