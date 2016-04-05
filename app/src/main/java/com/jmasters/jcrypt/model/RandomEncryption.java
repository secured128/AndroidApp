/**
 * 
 */
package com.jmasters.jcrypt.model;

import javax.crypto.SecretKey;

/**
 * @author alexb
 * 
 */
public class RandomEncryption {
	private Long id;
	private SecretKey key;

	public RandomEncryption(Long id, SecretKey key) {
		this.id = id;
		this.key = key;
	}

	public Long getId() {
		return id;
	}

	public SecretKey getKey() {
		return key;
	}

}
