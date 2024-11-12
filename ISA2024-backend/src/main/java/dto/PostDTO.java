package dto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Comment;

public class PostDTO {

	
	private String owner;
	private ArrayList<String> likes;
	private ArrayList<Comment> comments;
	private File imagePath;
	private LocalDateTime time;
	private String content;
	private Float location_x;
	private Float location_y;
	
	
	public PostDTO() {
		super();
	}
	
	


	public PostDTO(String owner, ArrayList<String> likes, ArrayList<Comment> comments, File imagePath,
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


	public ArrayList<String> getLikes() {
		return likes;
	}


	public void setLikes(ArrayList<String> likes) {
		this.likes = likes;
	}


	public ArrayList<Comment> getComments() {
		return comments;
	}


	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}


	public File getImagePath() {
		return imagePath;
	}


	public void setImagePath(File imagePath) {
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