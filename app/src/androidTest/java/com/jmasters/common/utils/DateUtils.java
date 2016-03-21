package com.jmasters.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jmasters.common.finals.Constants;

public class DateUtils {

	public static String dateToXmlString(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return dateToXmlString(calendar);
		}
		return null;
	}

	public static String dateToXmlString(long timeInMillis) {
		return dateToString(timeInMillis, Constants.DEFAULT_XML_DATE_TIME_FORMAT);
	}

	public static String dateToString(long timeInMillis, SimpleDateFormat simpleDateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMillis);
		return dateToString(calendar, simpleDateFormat);
	}

	public static String dateToXmlString(Calendar calendar) {
		if (calendar != null) {
			return Constants.DEFAULT_XML_DATE_TIME_FORMAT.format(calendar.getTime());
		}
		return null;
	}

	public static String dateToString(Calendar calendar, SimpleDateFormat simpleDateFormat) {
		if (calendar != null && simpleDateFormat != null) {
			return simpleDateFormat.format(calendar.getTime());
		}
		return null;
	}

	public static Calendar dateFromXmlString(String dateStr) throws ParseException {
		return dateFromString(dateStr, Constants.DEFAULT_XML_DATE_TIME_FORMAT);
	}

	public static Calendar dateFromString(String dateStr, SimpleDateFormat simpleDateFormat) throws ParseException {
		if (dateStr != null && simpleDateFormat != null) {
			Date date = simpleDateFormat.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
		return null;
	}

}
