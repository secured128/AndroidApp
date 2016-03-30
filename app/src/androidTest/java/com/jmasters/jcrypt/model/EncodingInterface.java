/**
 * 
 */
package com.jmasters.jcrypt.model;

import java.io.IOException;

/**
 * @author alexb
 * 
 */
public interface EncodingInterface {

	public char getIdentifyer();

	public String encode(byte[] data) throws IOException;

	public byte[] decode(String encodedString) throws IOException;

}
