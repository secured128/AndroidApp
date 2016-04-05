/**
 * 
 */
package com.jmasters.jcrypt.model;

public class DecryptedParameters {
	private EncryptedParameters encryptionParameters;
	private byte[] dataBytes;

	public DecryptedParameters(EncryptedParameters encryptionParameters, byte[] dataBytes) {
		this.encryptionParameters = encryptionParameters;
		this.dataBytes = dataBytes;
	}

	public EncryptedParameters getEncryptionParameters() {
		return encryptionParameters;
	}

	public void setEncryptionParameters(EncryptedParameters encryptionParameters) {
		this.encryptionParameters = encryptionParameters;
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}

	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}

}
