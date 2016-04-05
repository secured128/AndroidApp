/**
 * 
 */
package com.jmasters.jcrypt.model;

import com.jmasters.common.utils.NumberUtils;

public enum ENCRYPTION_SHEMA_VERSION {

	ENCRYPTION_SHEMA_VERSION_ID0(new byte[] { -15, 55, -109, 33, 3, -123, 44, 29, -33, 35, -12, 0 });

	private byte[] ids;

	// private ParametrizerInterface parametrizer;

	ENCRYPTION_SHEMA_VERSION(byte[] ids) {
		this.ids = ids;
		// this.parametrizer = parametrizer;
	}

	// public ParametrizerInterface getParametrizer() {
	// return parametrizer;
	// }

	// public static ENCRYPTION_SHEMA_VERSION getParametrizerById(byte id) throws IllegalArgumentException {
	// for (ENCRYPTION_SHEMA_VERSION parametrizer : ENCRYPTION_SHEMA_VERSION.values()) {
	// if (parametrizer.getIds() == id) {
	// return parametrizer;
	// }
	// }
	// throw new IllegalArgumentException("not existing parametrizer id : " + id);
	// }

	public static ENCRYPTION_SHEMA_VERSION getRandom() {
		return ENCRYPTION_SHEMA_VERSION.values()[NumberUtils.getRandomIntegerInRange(0,
				ENCRYPTION_SHEMA_VERSION.values().length - 1)];
	}

	public byte[] getIds() {
		return ids;
	}
}