/**
 * 
 */
package com.jmasters.jcrypt.model;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * @author alexb
 * 
 */
public class ByteArrayReverserModifyer implements ByteArrayModifyerInterface {
	private int startIndex = 0;
	private int endIndex = Integer.MAX_VALUE;

	public ByteArrayReverserModifyer(int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public ByteArrayReverserModifyer() {

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
		if (currStartIndex >= currEndIndex) {
			ArrayUtils.reverse(byteArray);
			return byteArray;
		} else {
			byte[] subArrayPrefix = Arrays.copyOfRange(byteArray, 0, currStartIndex);
			byte[] subArrayForReverced = Arrays.copyOfRange(byteArray, currStartIndex, currEndIndex);
			byte[] subArraySufix = Arrays.copyOfRange(byteArray, currEndIndex, byteArray.length);
			ArrayUtils.reverse(subArrayForReverced);
			return ArrayUtils.addAll(ArrayUtils.addAll(subArrayPrefix, subArrayForReverced), subArraySufix);
		}
	}

	@Override
	public byte[] unmodify(byte[] byteArray) {
		return modify(byteArray);
	}

	public static void main(String[] args) {
		String test = "";

		ByteArrayReverserModifyer modifier = new ByteArrayReverserModifyer(5, 16);
		for (int i = 0; i < 1000; i++) {
			try {
				test = test + i;
				byte[] bytes = test.getBytes();
				byte[] modified = modifier.modify(bytes);
				modifier.unmodify(modified);
			} catch (Throwable e) {
				System.out.println("i=" + i);
				e.printStackTrace();
			}
		}

		System.out.print("done");
		System.exit(-1);
	}

}
