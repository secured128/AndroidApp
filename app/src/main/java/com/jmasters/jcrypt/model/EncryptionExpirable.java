/**
 * 
 */
package com.jmasters.jcrypt.model;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jmasters.common.utils.DateUtils;
import com.jmasters.common.utils.LogUtils;

/**
 * @author alexb
 * 
 */
public class EncryptionExpirable extends Encryption {

	private static final long serialVersionUID = 1L;
	private Calendar expirationDate = Calendar.getInstance();

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public boolean isExpirable() {
		return true;
	}

	@Override
	public boolean isExpired() {
		Calendar now = Calendar.getInstance();
		if (this.expirationDate == null || expirationDate.before(now) || expirationDate.equals(now)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		Map<String, String> logdata = new LinkedHashMap<String, String>();
		logdata.put("key", this.key);
		logdata.put("algorithm", (this.algorithm == null) ? "" : this.algorithm.toString());
		logdata.put("creationDate", DateUtils.dateToXmlString(this.creationDate));
		logdata.put("expirationDate",
				(this.expirationDate == null) ? "" : DateUtils.dateToXmlString(this.expirationDate));
		logdata.put("isExpirable", Boolean.toString(isExpirable()));
		logdata.put("isExpired", Boolean.toString(isExpired()));
		return LogUtils.toLogString(logdata);
	}
}
