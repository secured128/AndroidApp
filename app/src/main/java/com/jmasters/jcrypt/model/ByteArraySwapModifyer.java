/**
 * 
 */
package com.jmasters.jcrypt.model;

/**
 * @author alexb
 * 
 */
public class ByteArraySwapModifyer implements ByteArrayModifyerInterface {
	private static final int SWAP_ALL = Integer.MIN_VALUE;
	private int numberOfBytesToSwap = 0;

	public ByteArraySwapModifyer(int numberOfBytesToSwap) {
		if (numberOfBytesToSwap > 0) {
			this.numberOfBytesToSwap = numberOfBytesToSwap;
		} else {
			this.numberOfBytesToSwap = SWAP_ALL;
		}
	}

	public ByteArraySwapModifyer() {
		this.numberOfBytesToSwap = SWAP_ALL;
	}

	@Override
	public byte[] modify(byte[] byteArray) {

		int currNumberOfBytesToSwap = this.numberOfBytesToSwap;

		if (byteArray.length > 1) {
			int endIndex = byteArray.length - 1;
			if (byteArray.length % 2 != 0) {
				endIndex = endIndex - 1;
			}

			if (this.numberOfBytesToSwap == SWAP_ALL || (endIndex + 1) / 2 < numberOfBytesToSwap) {
				currNumberOfBytesToSwap = (endIndex + 1) / 2;
			}

			for (int i = 0; i < currNumberOfBytesToSwap; i++) {
				byte currentByte = byteArray[i];
				byteArray[i] = byteArray[endIndex - i];
				byteArray[endIndex - i] = currentByte;
			}
		}
		return byteArray;
	}

	@Override
	public byte[] unmodify(byte[] byteArray) {
		return modify(byteArray);
	}

}
