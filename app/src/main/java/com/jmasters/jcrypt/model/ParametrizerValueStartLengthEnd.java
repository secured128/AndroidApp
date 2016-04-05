/**
 * 
 */
package com.jmasters.jcrypt.model;

import com.jmasters.jcrypt.JcryptManager;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Calendar;


/**
 * @author alexb
 * 
 */
public class ParametrizerValueStartLengthEnd extends ParametrizerAbstract {

	@Override
	public byte[] setParameters(byte[] byteArray, EncryptedParameters params) {
		Calendar expiryDate = params.getExpiryDate();
		if (expiryDate != null && JcryptManager.START_DATE.before(expiryDate)) {
			expiryDate.set(Calendar.AM_PM, 1);
			expiryDate.set(Calendar.MILLISECOND, 0);
			expiryDate.set(Calendar.SECOND, 0);
			long expiryDateParam = expiryDate.getTimeInMillis() - JcryptManager.START_DATE.getTimeInMillis();
			byte[] expiryDateArrayParam = JcryptManager.convertLongToCompressedReversedByteArray(expiryDateParam);
			byteArray = ArrayUtils.addAll(expiryDateArrayParam, byteArray);
			byteArray = ArrayUtils.addAll(byteArray, new byte[] { (byte) expiryDateArrayParam.length });
		} else {
			byteArray = JcryptManager.addEmptyParamToByteArrayEnd(byteArray);
		}

		if (params.getEncryptorId() != params.getDecryptorId()) {
			byte[] receiverIdDateArrayParam = JcryptManager.convertLongToCompressedReversedByteArray(params
					.getDecryptorId());
			byteArray = ArrayUtils.addAll(receiverIdDateArrayParam, byteArray);
			byteArray = ArrayUtils.addAll(byteArray, new byte[] { (byte) receiverIdDateArrayParam.length });
		} else {
			byteArray = JcryptManager.addEmptyParamToByteArrayEnd(byteArray);
		}

		byte[] ownerIdDateArrayParam = JcryptManager.convertLongToCompressedReversedByteArray(params.getEncryptorId());
		byteArray = ArrayUtils.addAll(ownerIdDateArrayParam, byteArray);
		byteArray = ArrayUtils.addAll(byteArray, new byte[] { (byte) ownerIdDateArrayParam.length });

		return byteArray;
	}

	@Override
	public DecryptedParameters getParameters(byte[] byteArray) {

		int encryptorIdParamLength = byteArray[byteArray.length - 1];
		byte[] encryptorIdReversedAndCompressedBytesOfLong = new byte[encryptorIdParamLength];
		encryptorIdReversedAndCompressedBytesOfLong = Arrays.copyOfRange(byteArray, 0, encryptorIdParamLength);
		long encryptorId = JcryptManager.getLongFromReversedBytes(encryptorIdReversedAndCompressedBytesOfLong);
		byteArray = Arrays.copyOfRange(byteArray, encryptorIdParamLength, byteArray.length - 1);

		long decryptorId = encryptorId;
		byte receiverIdParamLength = byteArray[byteArray.length - 1];
		if (!JcryptManager.isEmptyParam(receiverIdParamLength)) {
			byte[] receiverIdReversedAndCompressedBytesOfLong = new byte[receiverIdParamLength];
			receiverIdReversedAndCompressedBytesOfLong = Arrays.copyOfRange(byteArray, 0, receiverIdParamLength);
			decryptorId = JcryptManager.getLongFromReversedBytes(receiverIdReversedAndCompressedBytesOfLong);
			byteArray = Arrays.copyOfRange(byteArray, receiverIdParamLength, byteArray.length - 1);
		} else {
			byteArray = JcryptManager.removeByteFromByteArrayEnd(byteArray);
		}

		Calendar expiryDate = JcryptManager.START_DATE;
		byte expiryDateAdditionalMsParamLength = byteArray[byteArray.length - 1];
		if (!JcryptManager.isEmptyParam(expiryDateAdditionalMsParamLength)) {
			byte[] expiryDateAdditionalMsReversedAndCompressedBytesOfLong = new byte[expiryDateAdditionalMsParamLength];
			expiryDateAdditionalMsReversedAndCompressedBytesOfLong = Arrays.copyOfRange(byteArray, 0,
					expiryDateAdditionalMsParamLength);
			long expiryDateAdditionalMs = JcryptManager
					.getLongFromReversedBytes(expiryDateAdditionalMsReversedAndCompressedBytesOfLong);
			long expiryDateMs = JcryptManager.START_DATE.getTimeInMillis() + expiryDateAdditionalMs;
			expiryDate = Calendar.getInstance();
			expiryDate.setTimeInMillis(expiryDateMs);
			byteArray = Arrays.copyOfRange(byteArray, expiryDateAdditionalMsParamLength, byteArray.length - 1);
		} else {
			byteArray = JcryptManager.removeByteFromByteArrayEnd(byteArray);
		}
		EncryptedParameters encryptionParameters = new EncryptedParameters(encryptorId, decryptorId, expiryDate);
		return new DecryptedParameters(encryptionParameters, byteArray);
	}
}
