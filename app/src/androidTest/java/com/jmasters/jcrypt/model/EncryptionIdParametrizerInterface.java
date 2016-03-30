/**
 * 
 */
package com.jmasters.jcrypt.model;

/**
 * @author alexb
 * 
 */
public interface EncryptionIdParametrizerInterface {

	/**
	 * @param byteArray
	 * @param params
	 * @return
	 */
	public byte[] setEncryptionIdParameter(byte[] byteArray, long encryptionId);

	/**
	 * 
	 * @param byteArray
	 * @return
	 */
	public DecryptedEncryptionParameter getEncryptionIdParameter(byte[] byteArray);

}
