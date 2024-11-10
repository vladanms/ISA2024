package dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Comment;

public class PostDTO {

	
	private String user;
	private ArrayList<String> likes;
	private ArrayList<Comment> comments;
	private String imagePath;
	private LocalDateTime time;
	private String content;
	
	
	public PostDTO() {
		super();
	}
	
	


	public PostDTO(String user, ArrayList<String> likes, ArrayList<Comment> comments, String imagePath,
			LocalDateTime time, String content) {
		super();
		this.user = user;
		this.likes = likes;
		this.comments = comments;
		this.imagePath = imagePath;
		this.time = time;
		this.content = content;
	}




	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
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
	
	
	
}

