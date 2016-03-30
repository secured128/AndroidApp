/**
 * 
 */
package com.jmasters.jcrypt.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jmasters.common.utils.DateUtils;
import com.jmasters.common.utils.LogUtils;
import com.jmasters.common.utils.security.SymmetricEncryptionUtil;

/**
 * @author alexb
 * 
 */
public class Encryption implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String key = "";
	protected Calendar creationDate = Calendar.getInstance();

	protected SymmetricEncryptionUtil.ALGORITHM algorithm = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SymmetricEncryptionUtil.ALGORITHM getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(SymmetricEncryptionUtil.ALGORITHM algorithm) {
		this.algorithm = algorithm;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isExpirable() {
		return false;
	}

	public boolean isExpired() {
		return false;
	}

	@Override
	public String toString() {
		Map<String, String> logdata = new LinkedHashMap<String, String>();
		logdata.put("key", this.key);
		logdata.put("algorithm", (this.algorithm == null) ? "" : this.algorithm.toString());
		logdata.put("creationDate", DateUtils.dateToXmlString(this.creationDate));
		logdata.put("isExpirable", Boolean.toString(isExpirable()));
		logdata.put("isExpired", Boolean.toString(isExpired()));
		return LogUtils.toLogString(logdata);
	}
}
