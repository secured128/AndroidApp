/**
 * 
 */
package com.jmasters.jcrypt.model;


import com.jmasters.jcrypt.JcryptManager;

/**
 * @author alexb
 * 
 */
public class EncryptionIdParametrizerPrefix implements EncryptionIdParametrizerInterface {

	@Override
	public byte[] setEncryptionIdParameter(byte[] byteArray, long encryptionId) {
		byteArray = JcryptManager
				.addLongParamToByteArrayStartAsReversedAndCompressedWithLength(byteArray, encryptionId);
		return byteArray;
	}

	@Override
	public DecryptedEncryptionParameter getEncryptionIdParameter(byte[] byteArray) {
		long encryptionId = JcryptManager.getLongParamFromReversedAndCompressedWithLengthByteArrayStart(byteArray);
		byteArray = JcryptManager.removeBytesFromByteArrayStart(byteArray, byteArray[0] + 1);
		DecryptedEncryptionParameter encryptionParameter = new DecryptedEncryptionParameter(encryptionId, byteArray);
		return encryptionParameter;
	}
}
