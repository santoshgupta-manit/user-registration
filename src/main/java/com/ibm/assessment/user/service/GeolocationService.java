package com.ibm.assessment.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.assessment.user.model.Geolocation;

@Service
public class GeolocationService {
	private final String API_URL = "http://ip-api.com/json/";

	@Autowired
	private RestTemplate restTemplate;

	public GeolocationService() {
		this.restTemplate = new RestTemplate();
	}

	public Geolocation getGeolocation(String ipAddress) {
		String apiUrl = API_URL + ipAddress + "?fields=status,country,countryCode,city";
		return restTemplate.getForObject(apiUrl, Geolocation.class);
	}
}
