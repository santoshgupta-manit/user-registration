package com.ibm.assessment.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.ibm.assessment.user.model.Geolocation;

@ExtendWith(MockitoExtension.class)
public class GeolocationServiceTest {

	@InjectMocks
	private GeolocationService geolocationService;
	@Mock
	private RestTemplate restTemplate;

	@Test
	void test_getGeolocation() {
		when(restTemplate.getForObject("http://ip-api.com/json/ipAddress?fields=status,country,countryCode,city", Geolocation.class))
				.thenReturn(new Geolocation("status", "country", "countryCode", "city"));
		Geolocation response = geolocationService.getGeolocation("ipAddress");
		assertThat(response).isNotNull();
	}
}
