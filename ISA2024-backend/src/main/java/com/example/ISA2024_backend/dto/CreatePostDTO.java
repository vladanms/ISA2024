package com.example.ISA2024_backend.dto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreatePostDTO {

	@Schema(description = "Username of the post's creator")
	private String owner;
	@Schema(description = "Text content of the post")
	private String content;
	@Schema(description = "Image to be uploaded - copied into app directory and given a unique name")
	private MultipartFile image;
	@Schema(description = "X coordinates of the owner's location")
	private Float location_x;
	@Schema(description = "Y coordinates of the owner's location")
	private Float location_y;
	
	
	public CreatePostDTO() {
		super();
	}
	
	


	public CreatePostDTO(String owner, MultipartFile image, String content, Float location_x, Float location_y) {
		super();
		this.owner = owner;
		this.content = content;
		this.image = image;
		this.location_x = location_x;
		this.location_y = location_y;
	}




	public MultipartFile getImage() {
		return image;
	}




	public void setImage(MultipartFile image) {
		this.image = image;
	}




	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
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