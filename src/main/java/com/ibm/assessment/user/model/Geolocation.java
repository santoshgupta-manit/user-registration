package com.ibm.assessment.user.model;

public class Geolocation {
	private String status;
	private String country;
	private String countryCode;
	private String city;
	
	

	public Geolocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Geolocation(String status, String country, String countryCode, String city) {
		super();
		this.status = status;
		this.country = country;
		this.countryCode = countryCode;
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

	@Override
	public String toString() {
		return "Geolocation [status=" + status + ", country=" + country + ", countryCode=" + countryCode + ", city="
				+ city + "]";
	}

}
