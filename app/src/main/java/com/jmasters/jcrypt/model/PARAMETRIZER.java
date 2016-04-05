/**
 * 
 */
package com.jmasters.jcrypt.model;

import com.jmasters.common.utils.NumberUtils;

public enum PARAMETRIZER {

	PARAMETRIZER_ID0_SUFIX((byte) 16, new ParametrizerSufix()), PARAMETRIZER_ID1_PREFIX((byte) -35,
			new ParametrizerPrefix()), PARAMETRIZER_ID2_MIXED1((byte) 45, new ParametrizerMixed1()), PARAMETRIZER_ID3_MIXED2(
			(byte) 98, new ParametrizerMixed2()), PARAMETRIZER_ID4_VALUES_START_LENGTHS_END((byte) 21,
			new ParametrizerValueStartLengthEnd());

	private byte id;
	private ParametrizerInterface parametrizer;

	PARAMETRIZER(byte id, ParametrizerInterface parametrizer) {
		this.id = id;
		this.parametrizer = parametrizer;
	}

	public ParametrizerInterface getParametrizer() {
		return parametrizer;
	}

	public static PARAMETRIZER getById(byte id) throws IllegalArgumentException {
		for (PARAMETRIZER parametrizer : PARAMETRIZER.values()) {
			if (parametrizer.getId() == id) {
				return parametrizer;
			}
		}
		throw new IllegalArgumentException("not existing parametrizer id : " + id);
	}

	public static PARAMETRIZER getRandom() {
		return PARAMETRIZER.values()[NumberUtils.getRandomIntegerInRange(0, PARAMETRIZER.values().length - 1)];
	}

	public byte getId() {
		return id;
	}
}