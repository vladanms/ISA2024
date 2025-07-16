package com.example.ISA2024_backend.model;

import javax.persistence.Embeddable;

@Embeddable
public class Comment {

	private String users;
	private String content;
	
	
	public Comment() {
		super();
	}


	public Comment(String users, String content) {
		super();
		this.users = users;
		this.content = content;
	}


	public String getUsers() {
		return users;
	}


	public void setUsers(String users) {
		this.users = users;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
}
