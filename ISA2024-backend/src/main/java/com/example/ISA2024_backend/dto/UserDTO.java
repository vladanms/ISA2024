package com.example.ISA2024_backend.dto;


import java.util.ArrayList;

import com.example.ISA2024_backend.model.Post;

public class UserDTO {


	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String address;
	private String city;
	private String country;
	private ArrayList<String> followers;
	private ArrayList<String> followed;
	private ArrayList<Post> posts;

	
	
	public UserDTO() {
		super();
	}

	public UserDTO(String username, String password, String email, String name, String surname, String address,
			String city, String country, String verification, ArrayList<String> followers, ArrayList<String> followed,
			ArrayList<Post> posts) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.followers = followers;
		this.followed = followed;
		this.posts = posts;
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

	public ArrayList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<String> followers) {
		this.followers = followers;
	}

	public ArrayList<String> getFollowed() {
		return followed;
	}

	public void setFollowed(ArrayList<String> followed) {
		this.followed = followed;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
	
	
}
