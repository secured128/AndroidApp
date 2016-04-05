package com.jmasters.common.utils.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SymmetricEncryptionUtil {

	public enum ALGORITHM {
		DES, Blowfish, DESede
	};

	public static SecretKey generateKey(ALGORITHM algorithm) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm.toString());
		SecretKey key = keyGen.generateKey();
		return key;
	}

	public static byte[] getBytes(SecretKey key) {
		return key.getEncoded();
	}

	public static SecretKey getKey(byte[] keyBytes, ALGORITHM algorithm) {
		SecretKey key = new SecretKeySpec(keyBytes, algorithm.toString());
		return key;
	}

	public static byte[] encrypt(byte[] data, SecretKey key) throws IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		return encryptDecript(data, key, Cipher.ENCRYPT_MODE);
	}

	public static byte[] decript(byte[] data, SecretKey key) throws IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		return encryptDecript(data, key, Cipher.DECRYPT_MODE);
	}

	private static byte[] encryptDecript(byte[] data, SecretKey key, int mode) throws IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance(key.getAlgorithm());
		cipher.init(mode, key);
		byte[] cipherData = cipher.doFinal(data);
		return cipherData;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SecretKey key = generateKey(ALGORITHM.DESede);

		byte[] bytes = getBytes(key);

		SecretKey key2 = getKey(bytes, ALGORITHM.DESede);

		System.out.print(key.equals(key2));
		return;
	}

}
