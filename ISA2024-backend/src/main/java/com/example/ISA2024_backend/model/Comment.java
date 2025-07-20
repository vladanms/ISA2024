package com.example.ISA2024_backend.model;

import javax.persistence.Embeddable;

@Embeddable
public class Comment {

	private String author;
	private String content;
	
	
	public Comment() {
		super();
	}


	public Comment(String author, String content) {
		super();
		this.author = author;
		this.content = content;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
}
