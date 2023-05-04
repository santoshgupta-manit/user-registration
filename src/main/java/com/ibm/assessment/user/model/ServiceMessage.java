package com.ibm.assessment.user.model;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;


public class ServiceMessage {
	@ApiModelProperty(example = "SUCCESS")
	private String message;
	@ApiModelProperty(example = "200")
	private HttpStatus statusCode;
	@ApiModelProperty(example = "Request completed successfully.")
	private String description;
	
	public ServiceMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceMessage(String message, HttpStatus statusCode, String description) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
