/**
 * 
 */
package com.jmasters.common.utils;

/**
 * @author alexb
 *
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);;

	/**
	 * Validate hex with regular expression
	 * 
	 * @param email
	 *            email for validation
	 * @return true valid email, false invalid email
	 */
	public static boolean isValid(final String email) {
		return isValid(email, pattern);
	}

	public static boolean isValid(final String email, final Pattern emailPattern) {
		if (!StringUtils.isNullOrEmptyTrimmed(email)) {
			Matcher matcher = emailPattern.matcher(email);
			return matcher.matches();
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isValid(""));
		System.out.println(isValid("d"));
		System.out.println(isValid(null));
		System.out.println(isValid("asfas@sfasd"));
		System.out.println(isValid("asfas@sfasd.kk"));
	}

}