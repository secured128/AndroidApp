/**
 * 
 */
package com.jmasters.jcrypt.model;

public class DecryptedEncryptionParameter {
	private long encryptionId;
	private byte[] dataBytes;

	public DecryptedEncryptionParameter(long encryptionId, byte[] dataBytes) {
		this.encryptionId = encryptionId;
		this.dataBytes = dataBytes;
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}

	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}

	public long getEncryptionId() {
		return encryptionId;
	}

	public void setEncryptionId(long encryptionId) {
		this.encryptionId = encryptionId;
	}

}
