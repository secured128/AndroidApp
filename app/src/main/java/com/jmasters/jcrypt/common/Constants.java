package com.jmasters.jcrypt.common;

/**
 * @author alexb
 * 
 */
public interface Constants {

	public static final String START_PAGE = "index.jsp";
	public static final String MAIN_PAGE = "main.jsp";
	public static final String ENCRYPT_DECRYPT_ADMIN_PAGE = "/admin/manage.jsp";
	public static final String ENCRYPT_DECRYPT_PAGE = "encryptDecrypt.jsp";
	public static final String USERS_PAGE = "/admin/users.jsp";
	public static final String USER_PAGE = "/mng/user.jsp";
	public static final String PROFILE_PAGE = "/mng/profile.jsp";
	public static final String CLIENTS_PAGE = "/mng/clients.jsp";
	public static final String CLIENT_PAGE = "/mng/client.jsp";
	public static final String RESULT_PAGE = "/mng/result.jsp";

	public static final String JCRYPT_FILE_EXTENTION = ".jcrypt";

	public static final String JCRYPT_APP_EMAIL = "app_email";
	public static final String JCRYPT_APP_URL = "app_url";
	public static final String JCRYPT_URL = "jcrypt_url";
	public static final String JCRYPT_SSL_URL = "jcrypt_ssl_url";

	public static final String JCRYPT_TITLE = "jcrypt_title";
	public static final String JCRYPT_MAX_INPUT_SIZE = "jcrypt_max_input_size";
	public static final String JCRYPT_DAILY_COUNTER_LIMIT = "jcrypt_daily_counter_limit";
	public static final String JMASTERS_URL = "jmasters_url";
	public static final String JMASTERS_SSL_URL = "jmasters_ssl_url";

	public static final String JCRYPT_APP_ID = "app_id";
	public static final String JCRYPT_USER_ID = "user_id";

	public static final String JCRYPT_TEXT_DATA = "text_data";
	public static final String JCRYPT_ENCRYPT_ACTION = "Encrypt";
	public static final String JCRYPT_DECRYPT_ACTION = "Decrypt";
	public static final String JCRYPT_FILE_FIELD_NAME = "file";

	public static final String JCRYPT_DECRYPTOR_FIELD_NAME = "decryptor";
	public static final String JCRYPT_EXPIRATION_DATE_FIELD_NAME = "expiration_date";
	public static final String JCRYPT_TEXT_EXPIRATION_DATE_FIELD_NAME = "text_expiration_date";
	public static final String JCRYPT_BINARY_EXPIRATION_DATE_FIELD_NAME = "binary_expiration_date";
	public static final String JCRYPT_TEXT_DECRYPTOR_FIELD_NAME = "text_decryptor";
	public static final String JCRYPT_BINARY_DECRYPTOR_FIELD_NAME = "binary_decryptor";

	public static final String JCRYPT_ENCRYPT_TEXT_BUTTON_LABLE = "Encrypt Text";
	public static final String JCRYPT_DECRYPT_TEXT_BUTTON_LABLE = "Decrypt Text";
	public static final String JCRYPT_ENCRYPT_FILE_BUTTON_LABLE = "Encrypt File";
	public static final String JCRYPT_DECRYPT_FILE_BUTTON_LABLE = "Decrypt File";

	public static final String JCRYPT_ENCRYPTED_TEXT_DATA = "encrypted_text_data";
	public static final String JCRYPT_DECRYPTED_TEXT_DATA = "decrypted_text_data";

	public static final String JCRYPT_FILE_NAME = "jcrypt_file_name";

	public static final String JCRYPT_ENCRYPTED_BINARY_DATA = "encrypted_binary_data";
	public static final String JCRYPT_DECRYPTED_BINARY_DATA = "decrypted_binary_data";

	public static final String JCRYPT_USER = "user";
	public static final String JCRYPT_USER_EMAIL = "user_email";
	public static final String JCRYPT_USER_PASSWORD = "user_password";
	public static final String JCRYPT_ADMIN_USER_NAME = "admin_user_name";
	public static final String JCRYPT_ADMIN_USER_PASSWORD = "admin_user_password";
	public static final String JCRYPT_AUTHORIZED_ACCESS = "is_authorised_access";
	public static final long JCRYPT_ADMIN_PROPERTIES_ID = 0;

	public static final String JCRYPT_SMTP_ACCOUNT = "smtp_account";
	public static final String JCRYPT_SMTP_PASSWORD = "smtp_password";
	public static final String JCRYPT_SMTP_HOST = "smtp_host";
	public static final String JCRYPT_SMTP_PORT = "smtp_port";

	public static final String JCRYPT_HTTP_REQUEST_LOCAL_NAME = "http_request_local_name";
	public static final String JCRYPT_HTTP_REQUEST_LOCAL_IP = "http_request_local_ip";

	// public static final String IS_DEBUG_MODE = "is_debug_mode";
	public static final String LOG4J_LEVEL = "log4j_level";

	public static final String JCRYPT_ADMIN_DIR_NAME = "admin_dir_name";
	public static final String JCRYPT_USERS_DIR_NAME = "users_dir_name";
	public static final String JCRYPT_CLIENT_FILE_NAME = "client_file_name";
	public static final String JCRYPT_CLIENT_FILE_BYTES = "client_file_bytes";

	public static final String JCRYPT_CLIENT_NAME = "client_name";
	public static final String JCRYPT_CLIENT_DESCRIPTION = "client_description";

	public static final String JCRYPT_CLIENT_MESSAGES = "client_messages";

	public static final String CLIENT_SELECTED_ACTION_ATTRIBUTE_NAME = "action";
	public static final String CLIENT_ACTION_TIME_OUT_ATTRIBUTE_NAME = "actionTimeOut";
	public static final String CLIENT_NEXT_CLIENT_WAKE_UP_ATTRIBUTE_NAME = "nextClientWakeUp";
	public static final String CLIENT_NEXT_CLIENT_WAKE_UP_UNIT_ATTRIBUTE_NAME = "nextClientWakeUpTimeUnit";
	public static final String CLIENT_ON_COMPLETE_ACTION_ATTRIBUTE_NAME = "onCompleteAction";

	public static final String SYMMETRYC_KEY_PROPERTY_NAME = "skey";
	public static final String ASYMMETRYC_PRIVATE_KEY = "aPrKey";
	public static final String ASYMMETRYC_PUBLIC_KEY = "aPuKey";
	public static final String JOPS_ROOT = "jops_root";
	public static final String JOPS_MANAGER = "jops_manager";
	public static final String JCRYPT_WS_APP_EMAIL = "app_email";
	public static final String JCRYPT_WS_APP_URL = "app_url";
	public static final String JCRYPT_WS_SSL_URL = "jcrypt_ws_ssl_url";

	public static final String JCRYPT_MODE = "mode";

	public static final String JCRYPT_WS_TITLE = "jcrypt_ws_title";
	public static final String JM_TITLE = "jm_title";
	public static final String JOPS_TITLE = "jops_title";
	// public static final String RAPID_SSL_TITLE = "rapid_ssl_title";
	public static final String POSITIVE_SSL_TITLE = "positive_ssl_title";

	public static final String JCRYPT_WS_APP_ID = "app_id";
	public static final String JCRYPT_WS_USER_ID = "user_id";

	public static final String JCRYPT_WS_TEXT_DATA_TO_ENCRYTPT = "text_data";
	public static final String JCRYPT_WS_ENCRIPT_BUTTON = "Encrypt";
	public static final String JCRYPT_WS_DECRIPT_BUTTON = "Decrypt";

	public static final String JCRYPT_WS_MAX_NUMBER_OF_RANDOM_ENCRYPTIONS = "enc_num";
	public static final String JCRYPT_WS_START_DATE = "start_date";

	public static final String JCRYPT_WS_ADMIN_USERS = "admin_users";

	public static final String JCRYPT_WS_USER = "user";
	public static final String JCRYPT_WS_USER_EMAIL = "user_email";
	public static final String JCRYPT_WS_USER_PASSWORD = "user_password";
	public static final String JCRYPT_WS_ADMIN_USER_NAME = JCRYPT_ADMIN_USER_NAME;
	public static final String JCRYPT_WS_ADMIN_USER_PASSWORD = JCRYPT_ADMIN_USER_PASSWORD;
	public static final String JCRYPT_WS_AUTHORIZED_ACCESS = "is_authorised_access";
	public static final String JCRYPT_WS_AUTHORIZED_USER = "authorized_user";
	public static final String JCRYPT_WS_IS_ADMIN = "is_admin";
	public static final String IS_USER_REGISTRATION_NOTIFICATION_SENT_TO_ADMIN = "is_user_registration_notification_sent_to_admin";
	public static final String WRITE_AUDIT_DATA = "write_audit_data";
	public static final String WRITE_ADMIN_AUDIT_DATA = "write_admin_audit_data";

	public static final long JCRYPT_WS_ADMIN_PROPERTIES_ID = 0;

	public static final String JCRYPT_WS_SMTP_ACCOUNT = "smtp_account";
	public static final String JCRYPT_WS_SMTP_PASSWORD = "smtp_password";
	public static final String JCRYPT_WS_SMTP_HOST = "smtp_host";
	public static final String JCRYPT_WS_SMTP_PORT = "smtp_port";

	public static final String JCRYPT_WS_IS_DEBUG_MODE = "is_debug_mode";

	public static final String JCRYPT_WS_ADMIN_DIR_NAME = "admin_dir_name";
	public static final String JCRYPT_WS_USERS_DIR_NAME = "users_dir_name";
	public static final String JCRYPT_WS_CLIENT_FILE_NAME = "client_file_name";
	public static final String JCRYPT_WS_CLIENT_FILE_BYTES = "client_file_bytes";

	public static final String USER_ATTRIBUTE_NAME = "user";
	public static final String USER_ID_ATTRIBUTE_NAME = "userId";
	public static final String USER_PASSWORD_ATTRIBUTE_NAME = "userPass";
	public static final String CLIENT_ID_ATTRIBUTE_NAME = "clientId";
	public static final String CLIENT_STATUS_REASON_ATTRIBUTE_NAME = "statusReason";
	public static final String FILE_ID_ATTRIBUTE_NAME = "fileId";
	public static final String FILE_NAME_ATTRIBUTE_NAME = "fileName";
	public static final String ACTION_EXECUTION_ID_ATTRIBUTE_NAME = "executionId";
	public static final String ACTION_DEFINITION_ID_ATTRIBUTE_NAME = "defenitionId";
	public static final String JCRYPT_WS_CLIENT_NAME = "client_name";
	public static final String JCRYPT_WS_CLIENT_DESCRIPTION = "client_description";

	public static final String EXECUTION_ID_REQUEST_ATTRIBUTE_NAME = "executionId";

	public static final String JCRYPT_WS_CLIENT_MESSAGES = "client_messages";

	public static final String IS_AUTHORIZED_ACCESS = "is_authorised_access";
	public static final String IS_ADMIN = "is_admin";
	public static final String AUTHORIZED_USER = "authorized_user";
	// public static final String USER_NICK = "user_nick";
	public static final String TEXT_DATA = "text_data";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_EMAIL_TYPE = "user_email_type";
	public static final String USER_EMAIL_TYPE_DESC = "user_email_type_DESC";
	// public static final String USER_PASSWORD = "user_password";

	public static final String IDENTITY_DELIMITER = "|";

}
