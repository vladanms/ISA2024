package com.example.ISA2024_backend.model;

import javax.persistence.Embeddable;

import io.swagger.v3.oas.annotations.media.Schema;

@Embeddable
public class Comment {

	@Schema(description = "Username of the author of a comment")
	private String author;
	@Schema(description = "Comment body")
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
