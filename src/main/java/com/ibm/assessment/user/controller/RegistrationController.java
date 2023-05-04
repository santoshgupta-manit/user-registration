package com.ibm.assessment.user.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.assessment.user.model.Geolocation;
import com.ibm.assessment.user.model.RegistrationRequest;
import com.ibm.assessment.user.model.RegistrationResponse;
import com.ibm.assessment.user.service.GeolocationService;
import com.ibm.assessment.user.service.MessageProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "RegistrationController", tags = "Registration Controller", description = "Manage user registration")
public class RegistrationController {
	@Autowired
	GeolocationService geolocationService;
	@Autowired
	MessageProvider messageProvider;

	@ApiOperation(value = "user registration")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request completed successfully"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(path = "/api/v1/user/register", produces = "application/json")
	public ResponseEntity<RegistrationResponse> register(
			@ApiParam(value = "Request object for user registration", required = true) @Valid @RequestBody RegistrationRequest userInfo) {
		Geolocation geolocation = geolocationService.getGeolocation(userInfo.getIpAddress());
		if (geolocation.getStatus().equalsIgnoreCase("fail")
				|| (null != geolocation.getCountryCode() && !geolocation.getCountryCode().equalsIgnoreCase("CA"))) {
			throw new IllegalArgumentException(messageProvider.getMessage("api.user.registration.not.allowed"));
		}
		return new ResponseEntity<>(new RegistrationResponse(UUID.randomUUID(),
				messageProvider.getMessage("api.user.registration.success"), geolocation.getCity()), HttpStatus.OK);
	}
}
