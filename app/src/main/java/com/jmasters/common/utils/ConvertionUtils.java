package com.jmasters.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class ConvertionUtils {

	public static byte[] objectToByteArray(Serializable obj) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		bos.close();
		bytes = bos.toByteArray();
		return bytes;
	}

	public static Object byteArrayToObject(byte[] byteData) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}

	/**
	 * Only Byte,Short,Integer,Long number formats supported
	 * 
	 * @param number
	 * @return
	 */
	public static byte[] numberToByteArray(Number number) {
		Class<? extends Number> dataType = number.getClass();
		int length;
		long value;
		if (Byte.class == dataType) {
			length = Byte.SIZE / Byte.SIZE;
			value = (Byte) number;
		} else if (Short.class == dataType) {
			length = Short.SIZE / Byte.SIZE;
			value = (Short) number;
		} else if (Integer.class == dataType) {
			length = Integer.SIZE / Byte.SIZE;
			value = (Integer) number;
		} else if (Long.class == dataType) {
			length = Long.SIZE / Byte.SIZE;
			value = (Long) number;
		} else
			throw new IllegalArgumentException(
					"Parameter must be one of the following types : Byte, Short, Integer, Long");
		byte[] byteArray = new byte[length];
		for (int i = 0; i < length; i++) {
			byteArray[i] = (byte) ((value >> (Byte.SIZE * (length - i - 1))) & 0xff);
		}
		return byteArray;
	}

	/**
	 * Input Byte Array must represent one of the following types : Byte, Short, Integer, Long
	 * 
	 * @param byteArray
	 * @return
	 */
	public static Number byteArrayToNumber(byte[] byteArray) {
		int lengthInBits = byteArray.length * Byte.SIZE;
		long value = 0L;
		if (Byte.SIZE != lengthInBits && Short.SIZE != lengthInBits && Integer.SIZE != lengthInBits
				&& Long.SIZE != lengthInBits) {
			throw new IllegalArgumentException(
					"Input Byte Array must represent one of the following types  : Byte, Short, Integer, Long");
		}
		for (int i = 0; i < byteArray.length; i++) {
			value = value << Byte.SIZE;
			value = (value | (byteArray[i] & 0xff));
		}
		if (Byte.SIZE == lengthInBits) {
			return (byte) value;
		} else if (Short.SIZE == lengthInBits) {
			return (short) value;
		} else if (Integer.SIZE == lengthInBits) {
			return (int) value;
		} else if (Long.SIZE == lengthInBits) {
			return (long) value;
		} else {
			return value;
		}
	}

	public static void main(String[] args) {
		byte[] byteArray = numberToByteArray((byte) 225);

		byte l = (Byte) byteArrayToNumber(byteArray);
		if (l < 0) {
			System.out.println(127 - l);
		} else {
			System.out.println(l);
		}
		// System.out.println(l);
		Random rand = new Random();
		rand.nextBytes(byteArray);
		System.exit(0);
	}

}
