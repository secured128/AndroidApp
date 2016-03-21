/**
 * 
 */
package com.jmasters.jcrypt.model;

import com.jmasters.common.utils.NumberUtils;

/**
 * @author alexb
 * 
 */
public enum MIDIFYER {

	MODIFIER_ID0((byte) 0, new ByteArrayModifyerInterface[] { new ByteArrayInvertionModifyer(0, 5),
			new ByteArraySwapModifyer(), new ByteArrayShiftModifyer(4, ByteArrayShiftModifyer.SHIFT_DIRECTION.RIGHT),
			new ByteArraySwapModifyer(5), new ByteArrayReverserModifyer(0, 40) }), MODIFIER_ID1((byte) 1,
			new ByteArrayModifyerInterface[] { new ByteArrayInvertionModifyer(0, 3),
					new ByteArrayReverserModifyer(5, 16),
					new ByteArrayShiftModifyer(8, ByteArrayShiftModifyer.SHIFT_DIRECTION.LEFT),
					new ByteArraySwapModifyer(5) }), MODIFIER_ID2((byte) 2, new ByteArrayModifyerInterface[] {
			new ByteArrayInvertionModifyer(10, 15),
			new ByteArrayShiftModifyer(32, ByteArrayShiftModifyer.SHIFT_DIRECTION.RIGHT),
			new ByteArrayReverserModifyer(0, 30), new ByteArraySwapModifyer(5) }), MODIFIER_ID3((byte) 3,
			new ByteArrayModifyerInterface[] { new ByteArrayInvertionModifyer(3, 5), new ByteArraySwapModifyer(10),
					new ByteArrayReverserModifyer(20, 29),
					new ByteArrayShiftModifyer(68, ByteArrayShiftModifyer.SHIFT_DIRECTION.LEFT), }), MODIFIER_ID4(
			(byte) 4, new ByteArrayModifyerInterface[] { new ByteArrayInvertionModifyer(8, 10),
					new ByteArrayShiftModifyer(74, ByteArrayShiftModifyer.SHIFT_DIRECTION.RIGHT),
					new ByteArraySwapModifyer(), new ByteArrayReverserModifyer(15, 60), new ByteArraySwapModifyer(5) }), MODIFIER_ID5(
			(byte) 5, new ByteArrayModifyerInterface[] { new ByteArrayInvertionModifyer(16, 30),
					new ByteArraySwapModifyer(12),
					new ByteArrayShiftModifyer(7, ByteArrayShiftModifyer.SHIFT_DIRECTION.LEFT),
					new ByteArrayReverserModifyer(0, 5), }), MODIFIER_ID6((byte) 6, new ByteArrayModifyerInterface[] {
			new ByteArrayShiftModifyer(9, ByteArrayShiftModifyer.SHIFT_DIRECTION.LEFT),
			new ByteArrayReverserModifyer(1, 28), new ByteArraySwapModifyer(5) }), MODIFIER_ID7((byte) 7,
			new ByteArrayModifyerInterface[] { new ByteArrayInvertionModifyer(5, 30),
					new ByteArrayShiftModifyer(3, ByteArrayShiftModifyer.SHIFT_DIRECTION.RIGHT),
					new ByteArrayReverserModifyer(1, 28), new ByteArraySwapModifyer(12) }), MODIFIER_ID8((byte) 8,
			new ByteArrayModifyerInterface[] {
					new ByteArrayShiftModifyer(20, ByteArrayShiftModifyer.SHIFT_DIRECTION.RIGHT),
					new ByteArrayInvertionModifyer(0, 5), new ByteArraySwapModifyer(6),
					new ByteArrayReverserModifyer(6, 40), new ByteArraySwapModifyer(5) }), MODIFIER_ID9((byte) 9,
			new ByteArrayModifyerInterface[] { new ByteArrayShiftModifyer(28,
					ByteArrayShiftModifyer.SHIFT_DIRECTION.RIGHT) }), MODIFIER_ID10((byte) 10,
			new ByteArrayModifyerInterface[] { new ByteArrayReverserModifyer(), new ByteArraySwapModifyer(5),
					new ByteArrayInvertionModifyer(6, 40) }), MODIFIER_ID11((byte) 11,
			new ByteArrayModifyerInterface[] { new ByteArrayInvertionModifyer(0, 40),
					new ByteArrayReverserModifyer(5, 15), new ByteArraySwapModifyer(10), }), MODIFIER_ID12((byte) 15,
			new ByteArrayModifyerInterface[] { new ByteArrayReverserModifyer(15, 150),
					new ByteArrayInvertionModifyer(2, 15), new ByteArraySwapModifyer(9), }), MODIFIER_ID13((byte) -45,
			new ByteArrayModifyerInterface[] { new ByteArrayReverserModifyer(0, 4),
					new ByteArrayInvertionModifyer(7, 20), new ByteArraySwapModifyer(3),
					new ByteArrayInvertionModifyer(0, 5) });

	private byte id;
	private ByteArrayModifyerInterface[] modifyers;

	MIDIFYER(byte id, ByteArrayModifyerInterface[] modifyers) {
		this.id = id;
		this.modifyers = modifyers;
	}

	public byte getId() {
		return this.id;
	}

	public static MIDIFYER getById(byte id) throws IllegalArgumentException {
		for (MIDIFYER byteArrayModifyer : MIDIFYER.values()) {
			if (byteArrayModifyer.getId() == id) {
				return byteArrayModifyer;
			}
		}
		throw new IllegalArgumentException("not supported encryption version id : " + id);
	}

	public static MIDIFYER getRandom() {
		return MIDIFYER.values()[NumberUtils.getRandomIntegerInRange(0, MIDIFYER.values().length - 1)];
	}

	public ByteArrayModifyerInterface[] getModifyers() {
		return modifyers;
	}

	public void setModifyers(ByteArrayModifyerInterface[] modifyers) {
		this.modifyers = modifyers;
	}
}
