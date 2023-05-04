package com.ibm.assessment.user.model;

import java.io.Serializable;
import java.util.UUID;


public class RegistrationResponse implements Serializable {
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID uuid;
	private String message;
	private String city;
	public RegistrationResponse(UUID uuid, String message, String city) {
		super();
		this.uuid = uuid;
		this.message = message;
		this.city = city;
	}
	
	

}
