/**
 * 
 */
package com.jmasters.jcrypt.model;

import com.jmasters.common.utils.NumberUtils;

public enum ENCRYPTION_ID_PARAMETRIZER {

	ENCRYPTION_ID_PARAMETRIZER_ID1_PREFIX((byte) -118, new EncryptionIdParametrizerPrefix());

	private byte id;
	private EncryptionIdParametrizerInterface parametrizer;

	ENCRYPTION_ID_PARAMETRIZER(byte id, EncryptionIdParametrizerInterface parametrizer) {
		this.id = id;
		this.parametrizer = parametrizer;
	}

	public EncryptionIdParametrizerInterface getParametrizer() {
		return parametrizer;
	}

	public static ENCRYPTION_ID_PARAMETRIZER getById(byte id) throws IllegalArgumentException {
		for (ENCRYPTION_ID_PARAMETRIZER parametrizer : ENCRYPTION_ID_PARAMETRIZER.values()) {
			if (parametrizer.getId() == id) {
				return parametrizer;
			}
		}
		throw new IllegalArgumentException("not existing parametrizer id : " + id);
	}

	public static ENCRYPTION_ID_PARAMETRIZER getRandom() {
		return ENCRYPTION_ID_PARAMETRIZER.values()[NumberUtils.getRandomIntegerInRange(0,
				ENCRYPTION_ID_PARAMETRIZER.values().length - 1)];
	}

	public byte getId() {
		return id;
	}
}