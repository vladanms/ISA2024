package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Post{
	
/*	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;*/
	
	//@Column(name = "owner", unique = false, nullable = false)
	private String user;
	
	//@OneToMany(fetch = FetchType.EAGER)
	private ArrayList<String> likes;
	
	//@OneToMany(fetch = FetchType.EAGER)
	private ArrayList<Comment> comments;
	
	//@Column(name = "imagePath", unique = false, nullable = false)
	private String imagePath;
	
	//@Column(name = "time", unique = false, nullable = false)
	private LocalDateTime time;
	
	//@Column(name = "content", unique = false, nullable = false)
	private String content;

	public Post() {
		super();
	}

	public Post(String user, String imagePath, String content) {
		super();
		this.user = user;
		this.imagePath = imagePath;
		this.content = content;
		this.time = LocalDateTime.now();
	}

/*	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

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

	public ArrayList<model.Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<model.Comment> comments) {
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