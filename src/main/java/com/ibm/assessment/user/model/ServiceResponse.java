package com.ibm.assessment.user.model;

public class ServiceResponse {

	private ServiceMessage message;

	public ServiceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceResponse(ServiceMessage message) {
		super();
		this.message = message;
	}

	public ServiceMessage getMessage() {
		return message;
	}

	public void setMessage(ServiceMessage message) {
		this.message = message;
	}
	
}
