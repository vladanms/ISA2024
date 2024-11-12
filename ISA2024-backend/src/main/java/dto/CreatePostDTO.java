package dto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Comment;

public class CreatePostDTO {

	
	private String owner;
	private String content;
	private File image;
	private Float location_x;
	private Float location_y;
	
	
	public CreatePostDTO() {
		super();
	}
	
	


	public CreatePostDTO(String owner, File image, String content, Float location_x, Float location_y) {
		super();
		this.owner = owner;
		this.content = content;
		this.image = image;
		this.location_x = location_x;
		this.location_y = location_y;
	}




	public File getImage() {
		return image;
	}




	public void setImage(File image) {
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