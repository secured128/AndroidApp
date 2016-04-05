/**
 * 
 */
package com.jmasters.jcrypt.common.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author alexb
 * 
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum STATUS {
		PREDEFINED, INIT, ACTIVE, DISABLED
	};

	private int passwordHash = -1;
	private String email = "";
	private Calendar registrationDate = Calendar.getInstance();
	private Calendar activationDate = null;
	private Calendar lastLoginDate = null;
	private STATUS status = STATUS.INIT;
	private int dailyCounter = 0;
	private Calendar counterDate;

	public int getDailyCounter() {
		return dailyCounter;
	}

	public void setDailyCounter(int dailyCounter) {
		this.dailyCounter = dailyCounter;
	}

	public Calendar getCounterDate() {
		return counterDate;
	}

	public void setCounterDate(Calendar counterDate) {
		this.counterDate = counterDate;
	}

	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Calendar getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Calendar lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public int getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "[User : email=" + this.email + ",status=" + this.status + " ]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Calendar activationDate) {
		this.activationDate = activationDate;
	}
}
