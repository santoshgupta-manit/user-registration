package com.ibm.assessment.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class RegistrationRequest {
	@ApiModelProperty(notes = "username", example = "simon", required = true)
	@NotBlank(message = "username must not be blank")
	private String username;
	@ApiModelProperty(notes = "password", example = "Pass#123", required = true)
	@NotBlank(message = "password must not be blank")
	@Size(min = 8, message = "password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%.]).*$", 
             message = "password must contain at least 1 number, 1 capitalized letter, and 1 special character in this set [_ # $ % .]")
    private String password;
	@ApiModelProperty(notes = "ipAddress", example = "24.48.0.11", required = true)
	@NotBlank(message = "ipAddress must not be blank")
	private String ipAddress;
	public String getUsername() {
		return username;
	}
	
	public RegistrationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationRequest(@NotBlank(message = "username must not be blank") String username,
			@NotBlank(message = "password must not be blank") @Size(min = 8, message = "password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%.]).*$", message = "password must contain at least 1 number, 1 capitalized letter, and 1 special character in this set [_ # $ % .]") String password,
			@NotBlank(message = "ipAddress must not be blank") String ipAddress) {
		super();
		this.username = username;
		this.password = password;
		this.ipAddress = ipAddress;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
    

}
