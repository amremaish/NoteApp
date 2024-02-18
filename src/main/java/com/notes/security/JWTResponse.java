package com.notes.security;

public class JWTResponse {

	private String token;
	private String phoneNumber;


	private String status;

	public JWTResponse(String token, String phoneNumber, String status) {
		this.status = status;
		this.token = token;
		this.phoneNumber = phoneNumber;
	}

	public JWTResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
