package com.jmasters.common.utils;

import com.jmasters.common.finals.Constants;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Pattern;


public class StringUtils {

	protected static final Pattern whitespacesRegex = Pattern.compile("\\s");

	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public static boolean isNullOrEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	public static boolean isNullOrEmptyTrimmed(String str) {
		return (isNullOrEmpty(str) || str.trim().length() == 0);
	}

	public static String padLeft(String original, char paddingChar, int length) {
		if (original != null && original.length() < length) {
			return createPaddingSequence(paddingChar, length - original.length()) + original;
		}
		return original;

	}

	public static String padRight(String original, char paddingChar, int length) {
		if (original != null && original.length() < length) {
			return original + createPaddingSequence(paddingChar, length - original.length());
		}
		return original;
	}

	private static String createPaddingSequence(char paddingChar, int length) {
		StringBuffer paddingSequence = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			paddingSequence.append(paddingChar);
		}
		return paddingSequence.toString();
	}

	/**
	 * removes both leading and trailing quotes only if both of them presented
	 * 
	 * @param original
	 * @return
	 */
	public static String removeLeadingAndTrailingQuotes(String original) {
		if (!isNullOrEmptyTrimmed(original)) {
			if (original.length() >= 2 && original.startsWith("\"") && original.endsWith("\"")) {
				return (String) original.subSequence(1, original.length() - 1);
			}
		}
		return original;

	}

	/**
	 * This method takes the given byte array and returns its hex representation in a newly created string. The string
	 * is prepended with zeros if the created hex number is not on a even basis (divides by 2).
	 * 
	 * @param bytes
	 *            the original byte array to be converted
	 * @return the hex string.
	 */
	public static String toHexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer();
		int len = bytes.length;
		int high = 0;
		int low = 0;
		for (int i = 0; i < len; i++) {
			high = ((bytes[i] & 0xf0) >> 4);
			low = (bytes[i] & 0x0f);
			buf.append(HEX_CHARS[high]);
			buf.append(HEX_CHARS[low]);
		}
		return buf.toString();
	}

	public static byte[] fromHexString(char[] hexChars) {
		int length = hexChars.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hexChars[i * 2], 16);
			int low = Character.digit(hexChars[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127)
				value -= 256;
			raw[i] = (byte) value;
		}
		return raw;
	}

	public static byte[] fromHexString(String hexString) {
		return fromHexString(hexString.toCharArray());
	}

	/**
	 * This method takes the given string and returns its hex representation in a newly created string, the string is
	 * prepended with zeros if the created hex number is not on a even basis (divides by 2) .
	 * 
	 * using UTF-8 charset for char to bytes conversion.
	 * 
	 * @param orgString
	 *            the original string to be fixed
	 * @return the fixed string.
	 */
	public static String toHexString(String orgString) {
		return toHexString(orgString.getBytes(Charset.forName("UTF-8")));
	}

	/**
	 * using UTF-8 charset for char to bytes conversion.
	 * 
	 * @param orgChar
	 * @return
	 */
	public static String toHexString(char orgChar) {
		return toHexString(String.valueOf(orgChar));
	}

	public static String toHexString(String orgString, Charset charset) {
		return toHexString(orgString.getBytes(charset));
	}

	public static String toHexString(char orgChar, Charset charset) {
		return toHexString(String.valueOf(orgChar), charset);
	}

	public static String toBase64String(InputStream is) throws IOException {
		StringBuffer sb = new StringBuffer();
		Base64 base64Encoder = new Base64();
		int bufferSize = 64;
		try {
			byte[] buffer = new byte[bufferSize];
			for (int n; (n = is.read(buffer)) != -1;) {
				byte[] tempBuffer;
				if (bufferSize == n) {
					tempBuffer = buffer;
				} else {
					tempBuffer = new byte[n];
					for (int i = 0; i < n; i++) {
						tempBuffer[i] = buffer[i];
					}
				}
				sb.append(base64Encoder.encode(tempBuffer)).append(Constants.STR_LF);
			}
			return sb.toString();

		} finally {
			is.close();
		}
	}

	public static String toBase64String(String string) throws IOException {
		return toBase64String(string.getBytes(Charset.forName("UTF-8")));
	}

	public static String toBase64String(byte[] dataToBeBase64Encoded) throws IOException {
		Base64 base64Encoder = new Base64();
		return new String(base64Encoder.encode(dataToBeBase64Encoded));
	}

	public static byte[] fromBase64String(String base64EncodedString) throws IOException {
		String[] dataLines = base64EncodedString.trim().split(Constants.STR_LF);
		String encodedString = dataLines[0];
		if (dataLines.length > 1) {
			for (int i = 1; i < dataLines.length; i++) {
				encodedString = encodedString + dataLines[i];
			}
			dataLines = null;
		}
		int dataLength = (encodedString.length() * 3) / 4;
		byte[] dataBytes = new byte[dataLength];
		dataBytes = Base64.decodeBase64(encodedString.getBytes("UTF-8"));
		return dataBytes;
	}

	public static String[] splitByLength(String src, int len) {
		String[] result = new String[(int) Math.ceil((double) src.length() / (double) len)];
		for (int i = 0; i < result.length; i++)
			result[i] = src.substring(i * len, Math.min(src.length(), (i + 1) * len));
		return result;
	}

	public static String removeWhiteSpaces(String src) {
		String oneLine = whitespacesRegex.matcher(src).replaceAll("");
		return oneLine;
	}

	public static void main(String[] args) throws IOException {
		String str = new String("A\u0045\u2188");

		String t0Base64 = toBase64String("klsjfaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(t0Base64);
		System.out.println(removeWhiteSpaces(t0Base64));
		System.out.println(str);
		System.out.println(toHexString(str.getBytes(Charset.forName("UTF-8"))));
		System.out.println(new String(fromBase64String(t0Base64)));

		System.out.println("OK");
	}

}
