package com.example.ISA2024_backend.dto;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegisterDTO {

	@Schema(description = "Unique username")
	private String username;
	@Schema(description = "Password")
	private String password;
	@Schema(description = "Unique e-mail")
	private String email;
	@Schema(description = "User's name")
	private String name;
	@Schema(description = "User's surname")
	private String surname;
	@Schema(description = "User's home address")
	private String address;
	@Schema(description = "User's home city")
	private String city;
	@Schema(description = "User's country")
	private String country;

	public RegisterDTO() {
		super();
	}

	public RegisterDTO(String username, String password, String email, String name, String surname, String address,
			String city, String country, String verification) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = country;

	}

	public String getUsername() {
		return username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}	
}
