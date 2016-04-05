/**
 * 
 */
package com.jmasters.jcrypt.model;

import java.io.Serializable;
import java.util.Calendar;

public class EncryptedParameters implements Serializable {

	private static final long serialVersionUID = 1L;

	private long encryptorId;
	private long decryptorId;
	private Calendar expiryDate;

	public EncryptedParameters() {

	}

	public EncryptedParameters(long encryptorId, long decryptorId, Calendar expiryDate) {
		this.encryptorId = encryptorId;
		this.decryptorId = decryptorId;
		this.expiryDate = expiryDate;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	public long getEncryptorId() {
		return encryptorId;
	}

	public void setEncryptorId(long encryptorId) {
		this.encryptorId = encryptorId;
	}

	public long getDecryptorId() {
		return decryptorId;
	}

	public void setDecryptorId(long decryptorId) {
		this.decryptorId = decryptorId;
	}

}
