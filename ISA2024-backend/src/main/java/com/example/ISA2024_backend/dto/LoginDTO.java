package com.example.ISA2024_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginDTO {

	@Schema(description = "Email or username")
	private String credentials;
	@Schema(description = "Password")
	private String password;
	
	public LoginDTO() {
		super();
	}

	public LoginDTO(String credentials, String password) {
		super();
		this.credentials = credentials;
		this.password = password;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
