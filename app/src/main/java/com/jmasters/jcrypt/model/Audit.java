/**
 * 
 */
package com.jmasters.jcrypt.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

import com.jmasters.common.utils.StringUtils;
import com.jmasters.common.finals.Constants;
import com.jmasters.common.utils.DateUtils;

public class Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final int NOT_SPECIFIED_VALUE = -1;

	private String creationDate = null;
	private String message = null;
	private int messageHash = NOT_SPECIFIED_VALUE;
	private long encryptorId = NOT_SPECIFIED_VALUE;
	private long decryptorId = NOT_SPECIFIED_VALUE;
	private ACTION action;
	private DATA_SOURCE dataSource;

	public static enum ACTION {
		ENCRYPT, DECRYPT
	};

	public static enum DATA_SOURCE {
		TEXT, BINARY
	};

	public Audit() {
	}

	public Audit(ACTION action, DATA_SOURCE dataSource, long encryptorId, long decryptorId, byte[] encryptedMessageBytes)
			throws IOException {
		this.action = action;
		this.dataSource = dataSource;
		this.encryptorId = encryptorId;
		this.decryptorId = decryptorId;
		this.message = StringUtils.toBase64String(encryptedMessageBytes);
		this.messageHash = message.hashCode();
		Calendar now = Calendar.getInstance();
		String nowString = DateUtils.dateToString(now, Constants.DEFAULT_SHORT_DATE_FORMAT);
		this.creationDate = nowString;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getMessageHash() {
		return messageHash;
	}

	public void setMessageHash(int messageHash) {
		this.messageHash = messageHash;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public ACTION getAction() {
		return action;
	}

	public void setAction(ACTION action) {
		this.action = action;
	}

	public DATA_SOURCE getDataSource() {
		return dataSource;
	}

	public void setDataSource(DATA_SOURCE dataSource) {
		this.dataSource = dataSource;
	}
}
