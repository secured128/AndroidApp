/**
 * 
 */
package com.jmasters.jcrypt.model;

/**
 * @author alexb
 * 
 */
public interface ByteArrayModifyerInterface {
	public abstract byte[] modify(byte[] byteArray);

	public abstract byte[] unmodify(byte[] byteArray);
}
