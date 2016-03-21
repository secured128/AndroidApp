/**
 * 
 */
package com.jmasters.jcrypt.model;

import com.jmasters.jcrypt.JcryptManager;

import java.util.Calendar;

/**
 * @author alexb
 * 
 */
public class ParametrizerMixed2 extends ParametrizerAbstract {

	@Override
	public byte[] setParameters(byte[] byteArray, EncryptedParameters params) {
		Calendar expiryDate = params.getExpiryDate();
		if (expiryDate != null && JcryptManager.START_DATE.before(expiryDate)) {
			expiryDate.set(Calendar.AM_PM, 1);
			expiryDate.set(Calendar.MILLISECOND, 0);
			expiryDate.set(Calendar.SECOND, 0);
			long expiryDateParam = expiryDate.getTimeInMillis() - JcryptManager.START_DATE.getTimeInMillis();
			byteArray = JcryptManager.addLongParamToByteArrayStartAsReversedAndCompressedWithLength(byteArray,
					expiryDateParam);
		} else {
			byteArray = JcryptManager.addEmptyParamToByteArrayStart(byteArray);
		}

		if (params.getEncryptorId() != params.getDecryptorId()) {
			byteArray = JcryptManager.addLongParamToByteArrayStartAsReversedAndCompressedWithLength(byteArray,
					params.getDecryptorId());
		} else {
			byteArray = JcryptManager.addEmptyParamToByteArrayStart(byteArray);
		}

		byteArray = JcryptManager.addLongParamToByteArrayEndAsReversedAndCompressedWithLength(byteArray,
				params.getEncryptorId());

		return byteArray;
	}

	@Override
	public DecryptedParameters getParameters(byte[] byteArray) {
		long encryptorId = JcryptManager.getLongParamFromReversedAndCompressedWithLengthByteArrayEnd(byteArray);
		byteArray = JcryptManager.removeBytesFromByteArrayEnd(byteArray, byteArray[byteArray.length - 1] + 1);
		long decryptorId = encryptorId;
		if (!JcryptManager.isEmptyParam(byteArray[0])) {
			decryptorId = JcryptManager.getLongParamFromReversedAndCompressedWithLengthByteArrayStart(byteArray);
			byteArray = JcryptManager.removeBytesFromByteArrayStart(byteArray, byteArray[0] + 1);
		} else {
			byteArray = JcryptManager.removeByteFromByteArrayStart(byteArray);
		}
		Calendar expiryDate = JcryptManager.START_DATE;
		if (!JcryptManager.isEmptyParam(byteArray[0])) {
			long expiryDateAdditionalMs = JcryptManager
					.getLongParamFromReversedAndCompressedWithLengthByteArrayStart(byteArray);
			long expiryDateMs = JcryptManager.START_DATE.getTimeInMillis() + expiryDateAdditionalMs;
			expiryDate = Calendar.getInstance();
			expiryDate.setTimeInMillis(expiryDateMs);
			byteArray = JcryptManager.removeBytesFromByteArrayStart(byteArray, byteArray[0] + 1);
		} else {
			byteArray = JcryptManager.removeByteFromByteArrayStart(byteArray);
		}
		EncryptedParameters encryptionParameters = new EncryptedParameters(encryptorId, decryptorId, expiryDate);
		return new DecryptedParameters(encryptionParameters, byteArray);
	}
}
