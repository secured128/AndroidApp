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
public class ByteArrayShiftModifyer implements ByteArrayModifyerInterface {
	public static enum SHIFT_DIRECTION {
		LEFT, RIGHT
	};

	private int numberOfShifts = 0;
	private SHIFT_DIRECTION shiftDirection = SHIFT_DIRECTION.LEFT;

	public ByteArrayShiftModifyer(int numberOfShifts, SHIFT_DIRECTION shiftDirection) {
		this.numberOfShifts = numberOfShifts;
		this.shiftDirection = shiftDirection;
	}

	private byte[] shiftLeft(int shiftLength, byte[] byteArray) {
		return ArrayUtils.addAll(Arrays.copyOfRange(byteArray, shiftLength, byteArray.length),
				Arrays.copyOfRange(byteArray, 0, shiftLength));
	}

	private byte[] shiftRight(int shiftLength, byte[] byteArray) {
		return ArrayUtils.addAll(Arrays.copyOfRange(byteArray, byteArray.length - shiftLength, byteArray.length),
				Arrays.copyOfRange(byteArray, 0, byteArray.length - shiftLength));
	}

	private int fixShiftLength(int byteArrayLength) {
		if (this.numberOfShifts > byteArrayLength) {
			return this.numberOfShifts % byteArrayLength;
		} else {
			return this.numberOfShifts;
		}
	}

	@Override
	public byte[] modify(byte[] byteArray) {
		if (this.numberOfShifts == byteArray.length || this.numberOfShifts == 0) {
			return byteArray;
		}
		int shiftLength = fixShiftLength(byteArray.length);
		if (SHIFT_DIRECTION.LEFT == shiftDirection) {
			return shiftLeft(shiftLength, byteArray);
		} else {
			return shiftRight(shiftLength, byteArray);
		}
	}

	@Override
	public byte[] unmodify(byte[] byteArray) {
		if (this.numberOfShifts == byteArray.length || this.numberOfShifts == 0) {
			return byteArray;
		}
		int shiftLength = fixShiftLength(byteArray.length);
		if (SHIFT_DIRECTION.LEFT == shiftDirection) {
			return shiftRight(shiftLength, byteArray);
		} else {
			return shiftLeft(shiftLength, byteArray);

		}
	}

}
