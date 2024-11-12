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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "owner", unique = false, nullable = false)
	private String owner;
	
	@OneToMany(fetch = FetchType.EAGER)
	private ArrayList<String> likes;
	
	@OneToMany(fetch = FetchType.EAGER)
	private ArrayList<Comment> comments;
	
	@Column(name = "imagePath", unique = false, nullable = false)
	private String imagePath;
	
	@Column(name = "time", unique = false, nullable = false)
	private LocalDateTime time;
	
	@Column(name = "content", unique = false, nullable = false)
	private String content;
	
	@Column(name = "location_x", unique = false, nullable = false)
	private Float location_x;
	
	@Column(name = "location_y", unique = false, nullable = false)
	private Float location_y;


	public Post() {
		super();
	}

	public Post(String owner, String imagePath, String content, Float location_x, Float location_y) {
		super();
		this.owner = owner;
		this.imagePath = imagePath;
		this.content = content;
		this.time = LocalDateTime.now();
		this.location_x = location_x;
		this.location_y = location_y;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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