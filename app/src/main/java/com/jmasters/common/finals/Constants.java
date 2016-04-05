package com.jmasters.common.finals;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public interface Constants {

	// chars
	public static final char CHAR_SLASH = '/';
	public static final char CHAR_BACKSLASH = '\\';
	public static final char CHAR_TAB = '\t';
	public static final char CHAR_CR = '\r';
	public static final char CHAR_LF = '\n';
	public static final char CHAR_COMMA = ',';
	public static final char CHAR_DOT = '.';
	public static final char CHAR_COLON = ':';
	public static final char CHAR_SEMICOLON = ';';
	public static final char CHAR_SPACE = ' ';
	public static final char CHAR_QUOTATION_MARK = '\"';
	public static final char CHAR_APOSTROPTHE = '\'';
	public static final char CHAR_UNDERSCORE = '_';
	public static final char CHAR_HYPHEN = '-';

	// strings
	public static final String STR_SLASH = String.valueOf(CHAR_SLASH);
	public static final String STR_BACKSLASH = String.valueOf(CHAR_BACKSLASH);
	public static final String STR_TAB = String.valueOf(CHAR_TAB);
	public static final String STR_CR = String.valueOf(CHAR_CR);
	public static final String STR_LF = String.valueOf(CHAR_LF);
	public static final String STR_CRLF = STR_CR + STR_LF;
	public static final String STR_COMMA = String.valueOf(CHAR_COMMA);
	public static final String STR_DOT = String.valueOf(CHAR_DOT);
	public static final String STR_COLON = String.valueOf(CHAR_COLON);
	public static final String STR_SEMICOLON = String.valueOf(CHAR_SEMICOLON);
	public static final String STR_SPACE = String.valueOf(CHAR_SPACE);
	public static final String STR_QUOTATION_MARK = String.valueOf(CHAR_QUOTATION_MARK);
	public static final String STR_UNDERSCORE = String.valueOf(CHAR_UNDERSCORE);
	public static final String STR_HYPHEN = String.valueOf(CHAR_HYPHEN);
	public static final String STR_APOSTROPTHE = String.valueOf(CHAR_APOSTROPTHE);
	public static final String STR_EMPTY = "";

	public static final Pattern REGEX_DOT = Pattern.compile("\\.");

	public static final Charset CharsetUTF8 = Charset.forName("UTF8");

	public static final SimpleDateFormat DEFAULT_XML_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static final SimpleDateFormat DEFAULT_XML_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	public static final SimpleDateFormat DEFAULT_SHORT_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

}
