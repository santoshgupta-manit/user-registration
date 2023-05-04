package com.ibm.assessment.user;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ibm.assessment.user.controller.RegistrationController;
import com.ibm.assessment.user.model.Geolocation;
import com.ibm.assessment.user.model.RegistrationRequest;
import com.ibm.assessment.user.service.GeolocationService;
import com.ibm.assessment.user.service.MessageProvider;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {

	@InjectMocks
	private RegistrationController registrationController;

	@Mock
	private GeolocationService geolocationService;
	@Mock
	MessageProvider messageProvider;

	@Test
	void test_register_200() {
		when(geolocationService.getGeolocation(Mockito.any()))
				.thenReturn(new Geolocation("success", "canada", "CA", "Toranto"));
		registrationController.register(new RegistrationRequest("username", "password", "ipAddress"));
	}

}
