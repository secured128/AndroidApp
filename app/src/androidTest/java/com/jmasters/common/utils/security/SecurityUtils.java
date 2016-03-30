package com.jmasters.common.utils.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;

import javax.security.cert.CertificateException;

import com.jmasters.common.utils.StringUtils;

public class SecurityUtils {
	// private static KeyStore keystore;
	// private static KeyPair keyPair;
	// private static Certificate cert;
	public static final String SIGN_ALGORITHM = "SHA1withRSA";
	public static final String HASH_ALGORITHM = "SHA-256";

	public enum HASH_ALGORITHM {
		MD5("MD5"), SHA("SHA"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");

		private String name;

		HASH_ALGORITHM(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	};

	private static final String ALPHANUMERIK_CHARACTERS = "014QWERTYUIOP9283LSAZXCV7BKJHGFDNMlkjhgfdsazxcv5bnmpoiuytrewq6";

	/**
	 * One-way Hash Algorithm
	 * 
	 * @param hashAlgorithm
	 * @param text
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] generateHash(HASH_ALGORITHM hashAlgorithm, String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		return generateHash(hashAlgorithm, text.getBytes("UTF-8"));
	}

	/**
	 * One-way Hash Algorithm
	 * 
	 * @param hashAlgorithm
	 * @param byteData
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] generateHash(HASH_ALGORITHM hashAlgorithm, byte[] byteData) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm.getName());
		messageDigest.update(byteData);
		return messageDigest.digest();
	}

	public static synchronized byte[] sign(File datafile, PrivateKey privateKey) throws Exception {
		FileInputStream fis = new FileInputStream(datafile);
		return sign(fis, privateKey);
	}

	public static synchronized byte[] sign(String datafile, PrivateKey privateKey) throws Exception {
		FileInputStream fis = new FileInputStream(datafile);
		return sign(fis, privateKey);
	}

	public static synchronized byte[] sign(FileInputStream fis, PrivateKey privateKey) throws Exception {
		Signature sig = Signature.getInstance(SIGN_ALGORITHM);
		sig.initSign(privateKey);
		byte[] dataBytes = new byte[1024];
		int nread = fis.read(dataBytes);
		while (nread > 0) {
			sig.update(dataBytes, 0, nread);
			nread = fis.read(dataBytes);
		}
		;
		return sig.sign();
	}

	public static boolean verify(File datafile, PublicKey publicKey, byte[] sigbytes) throws Exception {
		FileInputStream fis = new FileInputStream(datafile);
		return verify(fis, publicKey, sigbytes);
	}

	public static boolean verify(String datafile, PublicKey publicKey, byte[] sigbytes) throws Exception {
		FileInputStream fis = new FileInputStream(datafile);
		return verify(fis, publicKey, sigbytes);
	}

	public static boolean verify(FileInputStream fis, PublicKey publicKey, byte[] sigbytes) throws Exception {
		Signature sig = Signature.getInstance(SIGN_ALGORITHM);
		sig.initVerify(publicKey);
		byte[] dataBytes = new byte[1024];
		int nread = fis.read(dataBytes);
		while (nread > 0) {
			sig.update(dataBytes, 0, nread);
			nread = fis.read(dataBytes);
		}
		;
		return sig.verify(sigbytes);
	}

	public static KeyPair getKeyPairFromJKS(File jksFile, String storepass, String alias, String aliaspass)
			throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException,
			KeyStoreException, java.security.cert.CertificateException, UnrecoverableKeyException {
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream(jksFile), storepass.toCharArray());
		KeyPair keyPair = SecurityUtils.getKeyPair(ks, alias, aliaspass);
		return keyPair;
	}

	public static KeyPair getKeyPair(KeyStore keystore, String alias, String alias_password)
			throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		// Get private key
		char[] alias_password_char_array = null;
		if (alias_password != null) {
			alias_password_char_array = alias_password.toCharArray();
		}
		Key key = keystore.getKey(alias, alias_password_char_array);
		if (key instanceof PrivateKey) {
			// Get certificate of public key
			Certificate cert = keystore.getCertificate(alias);

			// Get public key
			PublicKey publicKey = cert.getPublicKey();

			// Return a key pair
			return new KeyPair(publicKey, (PrivateKey) key);
		}
		return null;
	}

	public static String generateRandomAlphanumericPassword(int passwordLength) {
		StringBuffer randomPassword = new StringBuffer(passwordLength);
		for (int i = 0; i < passwordLength; i++) {
			int charIndex = (int) (Math.random() * ALPHANUMERIK_CHARACTERS.length());
			if (charIndex == ALPHANUMERIK_CHARACTERS.length()) {
				charIndex = charIndex - 1;
			}
			randomPassword.append(ALPHANUMERIK_CHARACTERS.charAt(charIndex));
		}
		return randomPassword.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			KeyStore keystore = KeyStore.getInstance("jks");
			InputStream is = Class.forName("com.jmasters.utils.SecurityUtils").getResourceAsStream(
					"../../../keystore.jks");
			keystore.load(is, null);
			KeyPair keyPair = getKeyPair(keystore, "jmasters_ca", "h1d8r23");
			System.out.println("Public Key : " + keyPair.getPublic().toString());
			System.out.println("Private Key : " + keyPair.getPrivate().toString());
			String datafile = "D:/Documents/ResumeEngCom21MKSExclibris.doc";
			byte[] sigbytes = sign(datafile, keyPair.getPrivate());
			System.out.println();
			System.out.println("Signature(in hex) for '" + datafile + "' file :: " + StringUtils.toHexString(sigbytes));

			boolean result = verify(datafile, keyPair.getPublic(), sigbytes);
			System.out.println("Signature Verification Result = " + result);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
