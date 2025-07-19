package com.example.ISA2024_backend.dto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;


public class CreatePostDTO {

	
	private String owner;
	private String content;
	private MultipartFile image;
	private Float location_x;
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