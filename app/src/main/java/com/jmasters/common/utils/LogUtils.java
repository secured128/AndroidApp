package com.jmasters.common.utils;

import java.util.Calendar;
import java.util.Map;

import com.jmasters.common.finals.Constants;

public class LogUtils {

    private static final String START_AND_END_LOG_SECTION = "---------------------------------------------------------------";

    public static String toLogString(Map<String, String> keyValueMap, int tabsPading, boolean addDateTime) {
	StringBuffer str = new StringBuffer(Constants.STR_LF).append(START_AND_END_LOG_SECTION).append(Constants.STR_LF);
	if (addDateTime) {
	    str.append(StringUtils.padRight("Date Time", Constants.CHAR_TAB, tabsPading)).append(Constants.STR_COLON).append(
		    DateUtils.dateToXmlString(Calendar.getInstance())).append(Constants.STR_LF);
	}
	for (String key : keyValueMap.keySet()) {
	    str.append(StringUtils.padRight(key, Constants.CHAR_TAB, tabsPading)).append(Constants.STR_COLON).append(
		    keyValueMap.get(key)).append(Constants.STR_LF);
	}
	str.append(START_AND_END_LOG_SECTION).append(Constants.STR_LF);
	return str.toString();
    }

    public static String toLogString(Map<String, String> keyValueMap) {
	return toLogString(keyValueMap, 3, true);
    }

    public static String toLogString(Map<String, String> keyValueMap, boolean addDateTime) {
	return toLogString(keyValueMap, 3, addDateTime);
    }

}
