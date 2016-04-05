package com.jmasters.jcrypt.common.model;

public interface MessagesConstants {
	public static final long ERROR_GLOBAL = 9999999;
	public static final long CUSTOM_MESSAGE = 8888888;

	public static final long REGISTRATION_INFO_USER_SUCCESSFULY_REGISTERED = 1000000;
	public static final long REGISTRATION_INFO_ALREADY_REGISTERED_USER = 1000001;
	public static final long REGISTRATION_ERROR_USER_REGISTERED_FAILED = 1000002;
	public static final long LOGIN_ERROR_LOGIN_FAILED = 1000003;
	public static final long LOGIN_ERROR_INVALID_ID_PASSWORD_OR_NOT_REGISTERED = 1000004;
	public static final long REGISTRATION_INFO_CLIENT_SUCCESSFULY_REGISTERED = 1000005;
	public static final long REGISTRATION_ERROR_CLIENT_REGISTRATION_FAILED = 1000006;
	public static final long ERROR_MISSING_MANDATORY_FIELD = 1000007;
	public static final long ERROR_FAILD_CHANGE_CLIENT_STATUS = 1000008;
	public static final long ERROR_FAILD_CHANGE_USER_STATUS = 1000009;
	public static final long ERROR_DISABLED_USER_LOGIN = 1000010;
	public static final long ERROR_CLIENT_DOWNLOAD_FAILURE = 1000011;
	public static final long ERROR_ACTION_CRIATION_FAILURE = 1000012;
	public static final long ERROR_FAILD_TO_SEND_EMAIL = 1000013;
	public static final long ERROR_DELETING_ACTION_EXECUTION = 1000014;
	public static final long ERROR_DELETING_ACTION_DEFINITION = 1000015;
	public static final long ERROR_DELETING_CLIENT = 1000016;
	public static final long INFO_ONLY_DISABLED_CLIENT_CAN_BE_DELETED = 1000017;
	public static final long ERROR_DELETING_USER = 1000018;
	public static final long ERROR_WHILE_ADMINISTRATION_PROPERTY_UPDATE = 1000019;
	public static final long ERROR_INVALID_APPLICATION_URL = 1000020;
	public static final long LOGIN_ERROR_INVALID_ID = 1000021;

	public static final long ERROR_WS_NOT_SSL_ACCESS_RESTRICTED = 1100000;

	public static final long ERROR_ENCRYPTION_FAILURE = 1005000;
	public static final long ERROR_DECRYPTION_FAILURE = 1005001;
	public static final long ERROR_SECURITY_AUTHORIZATION_VIOLATION = 1005002;
	public static final long ERROR_CAN_NOT_FIND_CORRECT_ENCODING = 1005003;
	public static final long ERROR_UNSUPPORTED_ENCRYPTION_SCHEMA = 1005004;
	public static final long ERROR_EXPIRED_MESSAGE = 1005005;
	public static final long ERROR_JCRYPT_WS_FAILURE = 1005006;
	public static final long ERROR_EMPTY_PASSWORD = 1005007;
	public static final long ERROR_EMPTY_EMAIL = 1005008;
	public static final long ERROR_COUNTER_LIMIT_EXCEEDED = 1005009;
	public static final long ERROR_MISSING_OR_INVALID_EMAIL = 1005010;
	public static final long ERROR_OPERATION_FAILED_WITH_ERROR = 1005011;
	public static final long ERROR_FAILED_TO_FINED_USER_BY_ID = 1005012;
	public static final long ERROR_WHILE_ADMINISTRATION_PROPERTIES_RELOAD_VIA_WS = 1005013;
	public static final long ERROR_NOT_AUTHORIZED_ACCESS = 1005014;
	public static final long ERROR_MAX_INPUT_SIZE_EXCEEDED = 1005015;
	public static final long ERROR_FILE_NOT_SELECTED = 1005016;
	public static final long ERROR_INVALID_EXPIRATION_DATE = 1005017;

	// public static final long ERROR_INVALID_INITIATOR = 2000000;

}
