/**
 * 
 */
package com.jmasters.jcrypt.model;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author alexb
 * 
 */
public class ByteArrayInvertionModifyer implements ByteArrayModifyerInterface {
	private int startIndex = 0;
	private int endIndex = Integer.MAX_VALUE;

	public ByteArrayInvertionModifyer(int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public ByteArrayInvertionModifyer() {

	}

	@Override
	public byte[] modify(byte[] byteArray) {
		int currStartIndex = startIndex;
		int currEndIndex = endIndex;
		if (endIndex > byteArray.length) {
			currEndIndex = byteArray.length;
		}
		if (startIndex <= 0) {
			currStartIndex = 0;
		}
		if (startIndex >= endIndex) {
			currStartIndex = 0;
			currEndIndex = byteArray.length;
		}
		byte[] subArrayPrefix = Arrays.copyOfRange(byteArray, 0, currStartIndex);
		byte[] subArrayForInvertion = Arrays.copyOfRange(byteArray, currStartIndex, currEndIndex);
		byte[] subArraySufix = Arrays.copyOfRange(byteArray, currEndIndex, byteArray.length);
		for (int i = 0; i < subArrayForInvertion.length; i++) {
			subArrayForInvertion[i] = (byte) ~subArrayForInvertion[i];
		}
		return ArrayUtils.addAll(ArrayUtils.addAll(subArrayPrefix, subArrayForInvertion), subArraySufix);

	}

	@Override
	public byte[] unmodify(byte[] byteArray) {
		return modify(byteArray);
	}

}
