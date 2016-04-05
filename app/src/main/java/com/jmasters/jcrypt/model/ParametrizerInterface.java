/**
 * 
 */
package com.jmasters.jcrypt.model;


/**
 * @author alexb
 * 
 */
public interface ParametrizerInterface {

	/**
	 * @param byteArray
	 * @param params
	 * @return
	 */
	public byte[] setParameters(byte[] byteArray, EncryptedParameters params);

	/**
	 * 
	 * @param byteArray
	 * @return
	 */
	public DecryptedParameters getParameters(byte[] byteArray);

}
