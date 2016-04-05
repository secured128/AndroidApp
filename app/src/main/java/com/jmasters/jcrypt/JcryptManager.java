package com.jmasters.jcrypt;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.ArrayUtils;

import com.jmasters.common.utils.NumberUtils;
import com.jmasters.common.utils.StringUtils;
import com.jmasters.jcrypt.common.Constants;
import com.jmasters.jcrypt.common.JcryptEncriptionsV1;
import com.jmasters.jcrypt.common.exceptions.JCRYPTDecryptionException;
import com.jmasters.jcrypt.common.exceptions.JCRYPTEncryptionException;
import com.jmasters.jcrypt.common.exceptions.JCRYPTException;
import com.jmasters.jcrypt.common.model.MessagesConstants;
import com.jmasters.jcrypt.model.Audit;
import com.jmasters.jcrypt.model.DecryptedEncryptionParameter;
import com.jmasters.jcrypt.model.DecryptedParameters;
import com.jmasters.jcrypt.model.ENCRYPTION_ID_PARAMETRIZER;
import com.jmasters.jcrypt.model.EncryptedParameters;
import com.jmasters.jcrypt.model.Encryption;
import com.jmasters.jcrypt.model.MIDIFYER;
import com.jmasters.jcrypt.model.PARAMETRIZER;
import com.jmasters.jcrypt.model.RandomEncryption;
import com.jmasters.common.utils.ConvertionUtils;
import com.jmasters.common.utils.DateUtils;
import com.jmasters.common.utils.security.SymmetricEncryptionUtil;
import com.jmasters.common.utils.security.SymmetricEncryptionUtil.ALGORITHM;
import com.jmasters.jcrypt.model.ByteArrayModifyerInterface;
import com.jmasters.jcrypt.model.ENCRYPTION_SHEMA_VERSION;

/**
 * @author alexb
 * 
 */
public class JcryptManager implements Constants {
	private static String TAG = "JcryptManager.class";

	public static final byte LONG_PARAMETER_BYTE_SIZE = Long.SIZE / Byte.SIZE;
	public static Calendar START_DATE = null ;

	private static final int MAX_NUMBER_OF_RANDOM_ENCRYPTIONS = JcryptEncriptionsV1.NUMBER_OF_ENCRYPTIONS;

	private static SecretKey[] encryptionsCash = null;

	static {
		try {
			START_DATE =DateUtils.dateFromXmlString("2012-11-01T00:00:00.000+0200");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		init();
	}

	private static void init() {
		Map<Byte, PARAMETRIZER> paremetrizersMap = new ConcurrentHashMap<Byte, PARAMETRIZER>();
		for (PARAMETRIZER parametrizer : PARAMETRIZER.values()) {
			if (paremetrizersMap.containsKey(parametrizer.getId())) {
				throw new IllegalArgumentException("Parametryzer Id '" + parametrizer.getId() + "' is duplicate for "
						+ parametrizer + " and " + paremetrizersMap.get(parametrizer.getId()));
			} else {
				paremetrizersMap.put(parametrizer.getId(), parametrizer);
			}
		}
		paremetrizersMap = null;

		Map<Byte, ENCRYPTION_ID_PARAMETRIZER> encryptionIdParemetrizersMap = new ConcurrentHashMap<Byte, ENCRYPTION_ID_PARAMETRIZER>();
		for (ENCRYPTION_ID_PARAMETRIZER encryptionIdParemetrizer : ENCRYPTION_ID_PARAMETRIZER.values()) {
			if (encryptionIdParemetrizersMap.containsKey(encryptionIdParemetrizer.getId())) {
				throw new IllegalArgumentException("Encryption Id Paremetrizer Id '" + encryptionIdParemetrizer.getId()
						+ "' is duplicate for " + encryptionIdParemetrizer + " and "
						+ encryptionIdParemetrizersMap.get(encryptionIdParemetrizer.getId()));
			} else {
				encryptionIdParemetrizersMap.put(encryptionIdParemetrizer.getId(), encryptionIdParemetrizer);
			}
		}
		encryptionIdParemetrizersMap = null;

		Map<Byte, MIDIFYER> modifyerMap = new ConcurrentHashMap<Byte, MIDIFYER>();
		for (MIDIFYER modifyer : MIDIFYER.values()) {
			if (modifyerMap.containsKey(modifyer.getId())) {
				throw new IllegalArgumentException("Modifyer Id '" + modifyer.getId() + "' is duplicate for "
						+ modifyer + " and " + modifyerMap.get(modifyer.getId()));
			} else {
				modifyerMap.put(modifyer.getId(), modifyer);
			}
		}
		modifyerMap = null;
	}

	public static boolean isEmptyParam(byte paramByte) {
		if (paramByte > 0 && paramByte <= LONG_PARAMETER_BYTE_SIZE) {
			return false;

		} else {
			return true;
		}
	}

	public static byte getRandomEmptyParam() {
		if (new Random().nextBoolean()) {
			return (byte) NumberUtils.getRandomIntegerInRange(LONG_PARAMETER_BYTE_SIZE + 1, Byte.MAX_VALUE + 1);
		} else {
			return (byte) NumberUtils.getRandomIntegerInRange(Byte.MIN_VALUE, 1);
		}
	}

	public static synchronized RandomEncryption getRandomEncryption() {
		long encryptionId = NumberUtils.getRandomIntegerInRange(0, MAX_NUMBER_OF_RANDOM_ENCRYPTIONS - 1);
		return new RandomEncryption(encryptionId, JcryptEncriptionsV1.getEncryption((int)encryptionId));
	}

	public static synchronized SecretKey getEncryption( long encryptionId){
		return JcryptEncriptionsV1.getEncryption((int)encryptionId);
	}

	private static SecretKey getKey(Encryption encryption) throws IOException {
		return SymmetricEncryptionUtil.getKey(StringUtils.fromBase64String(encryption.getKey()),
				encryption.getAlgorithm());
	}

	protected static ALGORITHM getRandomSymetricAlgorythm() {
		int randomAlgIndex = NumberUtils.getRandomIntegerInRange(0, ALGORITHM.values().length - 1);
		return ALGORITHM.values()[randomAlgIndex];
	}

	public static byte[] convertLongToCompressedReversedByteArray(long longId) {
		byte[] longIdBytes = ConvertionUtils.numberToByteArray(longId);
		byte[] longIdNotNullReversedBytes = getNotNullReversedBytes(longIdBytes);
		return longIdNotNullReversedBytes;
	}

	protected static byte[] getNotNullReversedBytes(byte[] longBytes) {
		for (int i = 0; i < longBytes.length; i++) {
			if (longBytes[i] != 0) {
				byte[] notNullBytes = new byte[longBytes.length - i];
				System.arraycopy(longBytes, i, notNullBytes, 0, notNullBytes.length);
				byte[] reversedNotNullBytes = new byte[notNullBytes.length];
				for (int j = 0; j < notNullBytes.length; j++) {
					reversedNotNullBytes[notNullBytes.length - j - 1] = notNullBytes[j];
				}
				return reversedNotNullBytes;
			}
		}
		return new byte[] { (byte) 0 };
	}

	public static long getLongFromReversedBytes(byte[] reversedBytes) {
		return (Long) getNumberFromReversedBytes(reversedBytes, Long.SIZE / Byte.SIZE);
	}

	private static Number getNumberFromReversedBytes(byte[] reversedBytes, int sizeInBytes) {
		byte[] longBytes = new byte[sizeInBytes];
		for (int i = 0; i < reversedBytes.length; i++) {
			longBytes[longBytes.length - 1 - i] = reversedBytes[i];
		}
		return ConvertionUtils.byteArrayToNumber(longBytes);
	}

	private static byte[] addByteToByteArrayEnd(byte[] byteArrayData, byte lastByte) {
		byte[] oneByteArray = new byte[] { lastByte };
		byteArrayData = ArrayUtils.addAll(byteArrayData, oneByteArray);
		return byteArrayData;
	}

	private static byte[] addByteToByteArrayStart(byte[] byteArrayData, byte firstByte) {
		byte[] oneByteArray = new byte[] { firstByte };
		byteArrayData = ArrayUtils.addAll(oneByteArray, byteArrayData);
		return byteArrayData;
	}

	public static byte[] removeByteFromByteArrayEnd(byte[] byteArrayData) {
		return removeBytesFromByteArrayEnd(byteArrayData, 1);
	}

	public static byte[] removeByteFromByteArrayStart(byte[] byteArrayData) {
		return removeBytesFromByteArrayStart(byteArrayData, 1);
	}

	public static byte[] removeBytesFromByteArrayEnd(byte[] byteArrayData, int nemberOfBytesToRemove) {
		int endPos = byteArrayData.length;
		byteArrayData = Arrays.copyOf(byteArrayData, endPos - nemberOfBytesToRemove);
		return byteArrayData;
	}

	public static byte[] removeBytesFromByteArrayStart(byte[] byteArrayData, int nemberOfBytesToRemove) {
		byteArrayData = Arrays.copyOfRange(byteArrayData, nemberOfBytesToRemove, byteArrayData.length);
		return byteArrayData;
	}

	private static byte[] addByteArrayParamToByteArrayEnd(byte[] byteArrayData, byte[] byteArrayParam) {
		int endPos = byteArrayData.length;
		byteArrayData = Arrays.copyOf(byteArrayData, endPos + byteArrayParam.length + 1);
		for (int i = 0; i < byteArrayParam.length; i++) {
			byteArrayData[endPos + i] = byteArrayParam[i];
		}
		byteArrayData[byteArrayData.length - 1] = (byte) byteArrayParam.length;
		return byteArrayData;
	}

	public static byte[] addByteArrayParamToByteArrayStart(byte[] byteArrayData, byte[] byteArrayParam) {
		byteArrayParam = ArrayUtils.addAll(new byte[] { (byte) byteArrayParam.length }, byteArrayParam);
		byteArrayData = ArrayUtils.addAll(byteArrayParam, byteArrayData);
		return byteArrayData;
	}

	public static byte[] addLongParamToByteArrayEndAsReversedAndCompressedWithLength(byte[] byteArrayData,
			long longParam) {
		byte[] byteArrayParam = convertLongToCompressedReversedByteArray(longParam);
		byteArrayData = addByteArrayParamToByteArrayEnd(byteArrayData, byteArrayParam);
		return byteArrayData;
	}

	public static byte[] addLongParamToByteArrayStartAsReversedAndCompressedWithLength(byte[] byteArrayData,
			long longParam) {
		byte[] byteArrayParam = convertLongToCompressedReversedByteArray(longParam);
		byteArrayData = addByteArrayParamToByteArrayStart(byteArrayData, byteArrayParam);
		return byteArrayData;
	}

	public static long getLongParamFromReversedAndCompressedWithLengthByteArrayEnd(byte[] byteArrayData) {
		int paramLength = byteArrayData[byteArrayData.length - 1];
		byte[] reversedAndCompressedBytesOfLong = new byte[paramLength];
		reversedAndCompressedBytesOfLong = Arrays.copyOfRange(byteArrayData, byteArrayData.length - 1 - paramLength,
				byteArrayData.length - 1);
		long longParam = getLongFromReversedBytes(reversedAndCompressedBytesOfLong);
		return longParam;
	}

	public static long getLongParamFromReversedAndCompressedWithLengthByteArrayStart(byte[] byteArrayData) {
		int paramLength = byteArrayData[0];
		byte[] reversedAndCompressedBytesOfLong = new byte[paramLength];
		reversedAndCompressedBytesOfLong = Arrays.copyOfRange(byteArrayData, 1, paramLength + 1);
		long longParam = getLongFromReversedBytes(reversedAndCompressedBytesOfLong);
		return longParam;
	}

	public static byte[] addEmptyParamToByteArrayEnd(byte[] byteArrayData) {
		byteArrayData = ArrayUtils.addAll(byteArrayData, new byte[] { getRandomEmptyParam() });
		return byteArrayData;
	}

	public static byte[] addEmptyParamToByteArrayStart(byte[] byteArrayData) {
		byteArrayData = ArrayUtils.addAll(new byte[] { getRandomEmptyParam() }, byteArrayData);
		return byteArrayData;
	}

	public static String encrypt(long ownerID, long receiverID, Calendar expiryDate, String text)
			throws JCRYPTEncryptionException {
		try {
			byte[] encryptedBytes = encrypt(ownerID, receiverID, expiryDate, text.getBytes("UTF-8"), Audit.DATA_SOURCE.TEXT);
			String encodedString = StringUtils.toBase64String(encryptedBytes);
			return encodedString;
		} catch (UnsupportedEncodingException e) {
			throw new JCRYPTEncryptionException(e);
		} catch (IOException e) {
			throw new JCRYPTEncryptionException(e);
		} catch (JCRYPTEncryptionException e) {
			throw e;
		}
	}

	public static byte[] encrypt(long ownerId, long receiverID, Calendar expiryDate, byte[] byteArray)
			throws JCRYPTEncryptionException {
		return encrypt(ownerId, receiverID, expiryDate, byteArray, Audit.DATA_SOURCE.BINARY);
	}

	private static byte[] encrypt(long ownerId, long receiverID, Calendar expiryDate, byte[] byteArray,
			Audit.DATA_SOURCE dataSource) throws JCRYPTEncryptionException {

//		logger.setLevel(Level.toLevel(PropertiesManager.PROPERTIES.ADMIN.getProperty(Constants.LOG4J_LEVEL)));
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("===============ENCRYPT START====(" + DateUtils.dateToXmlString(Calendar.getInstance())
//					+ ")===========================");
//			logger.debug("ownerID : " + ownerId);
//			logger.debug("receiverID : " + receiverID);
//			logger.debug("expiryDate : " + ((expiryDate == null) ? "null" : DateUtils.dateToXmlString(expiryDate)));
//		}

		try {

			boolean isAdminUser = true;//UsersManager.isAdminUser(ownerId);
			if (!isAdminUser && byteArray.length > JCRYPTException.MAX_INPUT_SIZE_IN_BYTES) {
				throw new JCRYPTEncryptionException(MessagesConstants.ERROR_MAX_INPUT_SIZE_EXCEEDED,
						new String[] { JCRYPTException.MAX_INPUT_SIZE_IN_KILOBYTES_STRING });
			}

//			JopsManagerInterface jopsManager = JCRYPTConfiguration.getJopsManager();
			RandomEncryption randomEncryption = getRandomEncryption();
			PARAMETRIZER parametrizer = PARAMETRIZER.getRandom();
			byteArray = parametrizer.getParametrizer().setParameters(byteArray,
					new EncryptedParameters(ownerId, receiverID, expiryDate));
			byteArray = addByteToByteArrayEnd(byteArray, parametrizer.getId());
			byteArray = SymmetricEncryptionUtil.encrypt(byteArray, randomEncryption.getKey());
			ENCRYPTION_ID_PARAMETRIZER encryptionIdParametrizer = ENCRYPTION_ID_PARAMETRIZER.getRandom();
			byteArray = encryptionIdParametrizer.getParametrizer().setEncryptionIdParameter(byteArray,
					randomEncryption.getId());
			byteArray = addByteToByteArrayStart(byteArray, encryptionIdParametrizer.getId());
			MIDIFYER modifyer = MIDIFYER.getRandom();
//			if (logger.isDebugEnabled()) {
//				logger.debug("MIDIFYER : " + modifyer.name());
//			}
			for (ByteArrayModifyerInterface byteArrayModifyer : modifyer.getModifyers()) {
				byteArray = byteArrayModifyer.modify(byteArray);
//				if (logger.isDebugEnabled()) {
//					logger.debug("\t" + byteArrayModifyer);
//				}

			}
			byteArray = addByteToByteArrayStart(byteArray, modifyer.getId());

			byteArray = addByteToByteArrayStart(byteArray,
					ENCRYPTION_SHEMA_VERSION.ENCRYPTION_SHEMA_VERSION_ID0.getIds()[NumberUtils.getRandomIntegerInRange(
							0, ENCRYPTION_SHEMA_VERSION.ENCRYPTION_SHEMA_VERSION_ID0.getIds().length - 1)]);
			// test encryption
			// decrypt(receiverID, byteArray);
//			if (logger.isDebugEnabled()) {
//				logger.debug("PARAMETRIZER : " + parametrizer.name());
//				logger.debug("ENCRYPTION_ID_PARAMETRIZER : " + encryptionIdParametrizer.name());
//				logger.debug("output : " + StringUtils.toBase64String(byteArray));
//				logger.debug("===============ENCRYPT END======(" + DateUtils.dateToXmlString(Calendar.getInstance())
//						+ ")===========================");
//			}

//			AuditManager.createAuditRecord(ACTION.ENCRYPT, dataSource, ownerId, receiverID, isAdminUser, byteArray);
			return byteArray;
			// } catch (JCRYPTDecryptionException e) {
			// throw new JCRYPTEncryptionException(e);
		} catch (Throwable e) {
//			logger.fatal("ERROR WHILE ENCRYPTION BY USER ID " + ownerId + " FOR USER ID " + receiverID, e);
			throw new JCRYPTEncryptionException(e);
		}
	}

	public static String decrypt(long userId, String text) throws JCRYPTDecryptionException {
		try {
			text = StringUtils.removeWhiteSpaces(text);
			byte[] decodedBytes = StringUtils.fromBase64String(text);
			return new String(decrypt(userId, decodedBytes, Audit.DATA_SOURCE.TEXT), "UTF-8");
		} catch (JCRYPTDecryptionException e) {
			throw e;
		} catch (Throwable e) {
			throw new JCRYPTDecryptionException(e);
		}
	}

	public static byte[] decrypt(long decryptorId, byte[] byteArray) throws JCRYPTDecryptionException {
		return decrypt(decryptorId, byteArray, Audit.DATA_SOURCE.BINARY);
	}

	/**
	 * @param decryptorId
	 * @param decode
	 * @return
	 */
	private static byte[] decrypt(long decryptorId, byte[] byteArray, Audit.DATA_SOURCE dataSource)
			throws JCRYPTDecryptionException {

//		logger.setLevel(Level.toLevel(PropertiesManager.PROPERTIES.ADMIN.getProperty(Constants.LOG4J_LEVEL)));

		byte[] originalByteArray = ArrayUtils.clone(byteArray);
//		if (logger.isDebugEnabled()) {
//			logger.debug("===============DECRYPT START====(" + DateUtils.dateToXmlString(Calendar.getInstance())
//					+ ")===========================");
//			logger.debug("input :");
//			try {
//				logger.debug(StringUtils.toBase64String(byteArray));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		try {
			if (!ArrayUtils.contains(ENCRYPTION_SHEMA_VERSION.ENCRYPTION_SHEMA_VERSION_ID0.getIds(), byteArray[0])) {
				throw new JCRYPTDecryptionException(MessagesConstants.ERROR_DECRYPTION_FAILURE);
			} else {
				byteArray = removeByteFromByteArrayStart(byteArray);
			}
			byte modifyerId = byteArray[0];
			byteArray = removeByteFromByteArrayStart(byteArray);
			MIDIFYER modifyer = MIDIFYER.getById(modifyerId);

//			logger.debug("MIDIFYER : " + modifyer.name());

			for (int i = modifyer.getModifyers().length - 1; i >= 0; i--) {
				byteArray = modifyer.getModifyers()[i].unmodify(byteArray);
//				if (logger.isDebugEnabled()) {
//					logger.debug("\t" + modifyer.getModifyers()[i]);
//				}
			}
			ENCRYPTION_ID_PARAMETRIZER encryptionIdParametrizer = ENCRYPTION_ID_PARAMETRIZER.getById(byteArray[0]);
			byteArray = removeByteFromByteArrayStart(byteArray);
			DecryptedEncryptionParameter decryptedEncryptionParameter = encryptionIdParametrizer.getParametrizer()
					.getEncryptionIdParameter(byteArray);
			long encryptionId = decryptedEncryptionParameter.getEncryptionId();
			byteArray = decryptedEncryptionParameter.getDataBytes();

			SecretKey encryptionKey = getEncryption(encryptionId);
			byteArray = SymmetricEncryptionUtil.decript(byteArray, encryptionKey);
			byte parametrizerId = byteArray[byteArray.length - 1];
			byteArray = removeByteFromByteArrayEnd(byteArray);
			PARAMETRIZER parametrizer = PARAMETRIZER.getById(parametrizerId);
			DecryptedParameters decryptedParametersAndData = parametrizer.getParametrizer().getParameters(byteArray);
//			if (logger.isDebugEnabled()) {
//				logger.debug("ownerID : " + decryptedParametersAndData.getEncryptionParameters().getEncryptorId());
//				logger.debug("receiverID : " + decryptedParametersAndData.getEncryptionParameters().getDecryptorId());
//				logger.debug("expiryDate : "
//						+ DateUtils.dateToXmlString(decryptedParametersAndData.getEncryptionParameters()
//								.getExpiryDate()));
//			}
//			if (logger.isDebugEnabled()) {
//				logger.debug("PARAMETRIZER : " + parametrizer.name());
//				logger.debug("ENCRYPTION_ID_PARAMETRIZER : " + encryptionIdParametrizer.name());
//				// logger.trace("DECRYPTED DATA : " + new String(decryptedParametersAndData.getDataBytes()));
//				logger.debug("===============DECRYPT END======(" + DateUtils.dateToXmlString(Calendar.getInstance())
//						+ ")===========================");
//			}
			boolean isAdminUser = true;//UsersManager.isAdminUser(decryptorId);
			if (!isAdminUser) {
				if (decryptorId != decryptedParametersAndData.getEncryptionParameters().getDecryptorId()
						&& decryptorId != decryptedParametersAndData.getEncryptionParameters().getEncryptorId()) {
					throw new JCRYPTDecryptionException(MessagesConstants.ERROR_SECURITY_AUTHORIZATION_VIOLATION);
				}
				if (decryptorId != decryptedParametersAndData.getEncryptionParameters().getEncryptorId()
						&& Calendar.getInstance().after(
								decryptedParametersAndData.getEncryptionParameters().getExpiryDate())) {
					throw new JCRYPTDecryptionException(MessagesConstants.ERROR_EXPIRED_MESSAGE);
				}
			}
//			AuditManager.createAuditRecord(ACTION.DECRYPT, dataSource, decryptedParametersAndData
//					.getEncryptionParameters().getEncryptorId(), decryptedParametersAndData.getEncryptionParameters()
//					.getDecryptorId(), isAdminUser, originalByteArray);

			return decryptedParametersAndData.getDataBytes();
		} catch (JCRYPTDecryptionException e) {
			throw e;
		} catch (Throwable e) {
//			logger.fatal("ERROR WHILE DECRYPTION BY USER ID " + decryptorId, e);
			throw new JCRYPTDecryptionException(e);
		}
	}

	// private static void createAuditRecord(Audit.ACTION action, long encryptorId, long decryptorId, boolean
	// isAdminUser,
	// byte[] encryptedMessageBytes) {
	// try {
	// boolean writeToLog = "true".equalsIgnoreCase(PropertiesManager.PROPERTIES.ADMIN
	// .getProperty(WRITE_AUDIT_DATA));
	// boolean writeToAdminLog = "true".equalsIgnoreCase(PropertiesManager.PROPERTIES.ADMIN
	// .getProperty(WRITE_ADMIN_AUDIT_DATA));
	// if ((!isAdminUser && writeToLog) || (isAdminUser && writeToAdminLog)) {
	// JopsManagerInterface jopsManager = JCRYPTConfiguration.getJopsManager();
	// Audit audit = new Audit(action, encryptorId, decryptorId, encryptedMessageBytes);
	// jopsManager.storeObject(audit);
	// }
	// } catch (Throwable e) {
	// logger.error("Can not write audit data for action = '" + action + "' encryptorId=" + encryptorId
	// + ", decryptorId=" + decryptorId + " because of following exception : " + e.getMessage());
	// e.printStackTrace();
	// }
	// }

	// read audit record
	// int id = 60;
	// JopsManagerInterface jopsManager = JCRYPTConfiguration.getJopsManager();
	// Audit audit = (Audit) jopsManager.retrieveObject(Audit.class, id);
	// IoUtils.writeBinaryFile(StringUtils.fromBase64String(audit.getMessage()), new File("d:/temp/" + id
	// + ".bin.jcrypt"));

	// decrypt binary
	// decrypt(0, audit.getMessage().getBytes());

	// encript Text

	public static void main(String[] args) throws JCRYPTDecryptionException,
			JCRYPTEncryptionException, InterruptedException, NoSuchAlgorithmException, IOException {
//		Context.getSystemService(Context.TELEPHONY_SERVICE);
		// String encrypted = encrypt(3, 3, null, "test");
		// String decrypted = decrypt(3, encrypted);
		// System.out.println(decrypted);

		try {
			long usetId = 3;
			File file = null;
			// =====ENCRYPT=============
			// file = new File("d:\\temp\\secret\\secret.txt");
			// if (file.exists()) {
			// Scanner sc = new Scanner(file, "UTF-8");
			// sc.useDelimiter("$^"); // regex matching nothing
			// String decryptedTxt = sc.next();
			// sc.close();
			// //
			// String secretEncrypted = encrypt(usetId, usetId, null, decryptedTxt);
			//
			// System.out.println(secretEncrypted);
			// // =======================================
			//
			// file = new File("d:\\temp\\secret\\secret.txt.jcrypt");
			//
			// if (file.exists()) {
			// file.delete();
			// }
			//
			// file.createNewFile();
			//
			// FileWriter fw = new FileWriter(file.getAbsoluteFile());
			// BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(secretEncrypted);
			// bw.close();
			//
			// System.out.println("Done");
			// }
			// =============DECRYPT==============
			file = new File("d:\\temp\\secret\\secret.txt.jcrypt");

			Scanner sc1 = new Scanner(file, "UTF-8");
			sc1.useDelimiter("$^"); // regex matching nothing
			String encrypteddTxt = sc1.next();
			sc1.close();
			String decrypted = decrypt(usetId, encrypteddTxt);
			System.out.println(decrypted);
			// // ======================

			// // =============DECRYPT BINARY==============
			// String outputFileName = "d:\\temp\\secret\\LEUMI_CARD_VISA_MEGASPORT_4822_FRONT.jpg";
			// Path outputFile = Paths.get(outputFileName);
			// Path inputFile = Paths.get(outputFileName + ".jcrypt");
			// FileInputStream fin = new FileInputStream(inputFile.toFile());
			// FileChannel fcin = fin.getChannel();
			// ByteBuffer buffer = ByteBuffer.allocate(1024);
			//
			// ArrayList<Byte> al = new ArrayList<Byte>();
			//
			// while (true) {
			// buffer.clear();
			// int r = fcin.read(buffer);
			// if (r == -1) {
			// break;
			// }
			// buffer.flip();
			// for (int i = 0; i < buffer.limit(); i++) {
			// al.add(buffer.get(i));
			// }
			// }
			//
			// byte[] dataToDecrypt = new byte[al.size()];
			// for (int i = 0; i < al.size(); i++) {
			// dataToDecrypt[i] = al.get(i);
			// }
			// byte[] decryptedData = decrypt(usetId, dataToDecrypt, DATA_SOURCE.BINARY);
			//
			// Files.write(outputFile, decryptedData);
			//
			// // ======================
		} catch (Throwable e) {
			e.printStackTrace();
		}

		//
		// byte[] decodedBytes = StringUtils.fromBase64String(encryptedTxt);
		// String secret = new String(decrypt(0, decodedBytes, DATA_SOURCE.TEXT), "UTF-8");

		//
		// SecretKey encryptionKey = getEncryption(JCRYPTConfiguration.getJopsManager(), 420);
		// byteArray = SymmetricEncryptionUtil.encrypt(byteArray, encryptionKey);

		// byte[] secretBytes = decrypt(0, encryptedTxt.getBytes());
		// String secretTxt = new String(secretBytes);
		// secretTxt = secretTxt + "\n\nhttps://www.itbit.com/\nusername : secured128\npassword : Jerusalem1!2@";
		// System.out.println(encrypt(0, 0, null, secretTxt));

		// audit.

		// IoUtils.forceDelete(new File("D:/opt/jops/jcrypt_jops_root/"));

		// System.out.println(decrypt(100, "hQOKd7FfxYGBUAYzsk0KZqthfGUHrk1xxJ+0y/Md/NJhhIQFAQ=="));

		// String data = "t";
		// Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, 2013);
		// cal.set(Calendar.MONTH, 9);
		// for (int i = 990; i < 1000; i++) {
		// long userID = i;
		// if (cal.get(Calendar.DAY_OF_MONTH) > 28) {
		// cal.set(Calendar.DAY_OF_MONTH, 1);
		// if (cal.get(Calendar.MONTH) >= 12) {
		// cal.set(Calendar.MONTH, 0);
		// } else {
		// cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		// }
		// } else {
		// cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		// }
		// data = data + String.valueOf(i);
		// String encryptedData = encrypt(userID, userID + 1, null, data);
		// // String decryptedData = decrypt(userID, encryptedData);
		// System.out.println("user ID : " + userID);
		// System.out.println("input : '" + data + "'");
		// System.out.println("data : '" + data + "'");
		// System.out.println("encryptedData : '" + encryptedData + "'");
		// System.out.println();
		// }
		System.exit(0);
	}

}
