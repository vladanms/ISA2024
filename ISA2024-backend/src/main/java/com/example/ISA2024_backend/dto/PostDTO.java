package com.example.ISA2024_backend.dto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import com.example.ISA2024_backend.model.Comment;

import io.swagger.v3.oas.annotations.media.Schema;


public class PostDTO {

	
	@Schema(description = "Username of the post's creator")
	private String owner;
	@Schema(description = "List of usernames ofu sers who liked this post")
	private List<String> likes;
	@Schema(description = "List of comments left on this post")
	private List<Comment> comments;
	@Schema(description = "Path to the post's image within the app directory")
	private String imagePath;
	@Schema(description = "Time of post creation, automatically set to LocalDateTime.Now() upon creation")
	private LocalDateTime time;
	@Schema(description = "Textual content of the post")
	private String content;
	@Schema(description = "X coordinates of the owner's location")
	private Float location_x;
	@Schema(description = "Y coordinates of the owner's location")
	private Float location_y;
	
	
	public PostDTO() {
		super();
	}
	
	


	public PostDTO(String owner, List<String> likes, List<Comment> comments, String imagePath,
			LocalDateTime time, String content, Float location_x, Float location_y) {
		super();
		this.owner = owner;
		this.likes = likes;
		this.comments = comments;
		this.imagePath = imagePath;
		this.time = time;
		this.content = content;
		this.location_x = location_x;
		this.location_y = location_y;
	}




	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public List<String> getLikes() {
		return likes;
	}


	public void setLikes(List<String> likes) {
		this.likes = likes;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public LocalDateTime getTime() {
		return time;
	}


	public void setTime(LocalDateTime time) {
		this.time = time;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}




	public Float getLocation_x() {
		return location_x;
	}




	public void setLocation_x(Float location_x) {
		this.location_x = location_x;
	}


	public Float getLocation_y() {
		return location_y;
	}


	public void setLocation_y(Float location_y) {
		this.location_y = location_y;
	}
	
	
	
}