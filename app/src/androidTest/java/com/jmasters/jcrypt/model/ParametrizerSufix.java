/**
 * 
 */
package com.jmasters.jcrypt.model;

import java.util.Calendar;

import com.jmasters.jcrypt.JcryptManager;


/**
 * @author alexb
 * 
 */
public class ParametrizerSufix extends ParametrizerAbstract {

	@Override
	public byte[] setParameters(byte[] byteArray, EncryptedParameters params) {
		Calendar expiryDate = params.getExpiryDate();
		if (expiryDate != null && JcryptManager.START_DATE.before(expiryDate)) {
			expiryDate.set(Calendar.AM_PM, 1);
			expiryDate.set(Calendar.MILLISECOND, 0);
			expiryDate.set(Calendar.SECOND, 0);
			long expiryDateParam = expiryDate.getTimeInMillis() - JcryptManager.START_DATE.getTimeInMillis();
			byteArray = JcryptManager.addLongParamToByteArrayEndAsReversedAndCompressedWithLength(byteArray,
					expiryDateParam);
		} else {
			byteArray = JcryptManager.addEmptyParamToByteArrayEnd(byteArray);
		}

		if (params.getEncryptorId() != params.getDecryptorId()) {
			byteArray = JcryptManager.addLongParamToByteArrayEndAsReversedAndCompressedWithLength(byteArray,
					params.getDecryptorId());
		} else {
			byteArray = JcryptManager.addEmptyParamToByteArrayEnd(byteArray);
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
		if (!JcryptManager.isEmptyParam(byteArray[byteArray.length - 1])) {
			decryptorId = JcryptManager.getLongParamFromReversedAndCompressedWithLengthByteArrayEnd(byteArray);
			byteArray = JcryptManager.removeBytesFromByteArrayEnd(byteArray, byteArray[byteArray.length - 1] + 1);
		} else {
			byteArray = JcryptManager.removeByteFromByteArrayEnd(byteArray);
		}
		Calendar expiryDate = JcryptManager.START_DATE;
		if (!JcryptManager.isEmptyParam(byteArray[byteArray.length - 1])) {
			long expiryDateAdditionalMs = JcryptManager
					.getLongParamFromReversedAndCompressedWithLengthByteArrayEnd(byteArray);
			long expiryDateMs = JcryptManager.START_DATE.getTimeInMillis() + expiryDateAdditionalMs;
			expiryDate = Calendar.getInstance();
			expiryDate.setTimeInMillis(expiryDateMs);
			byteArray = JcryptManager.removeBytesFromByteArrayEnd(byteArray, byteArray[byteArray.length - 1] + 1);
		} else {
			byteArray = JcryptManager.removeByteFromByteArrayEnd(byteArray);
		}
		EncryptedParameters encryptionParameters = new EncryptedParameters(encryptorId, decryptorId, expiryDate);
		return new DecryptedParameters(encryptionParameters, byteArray);
	}

}
