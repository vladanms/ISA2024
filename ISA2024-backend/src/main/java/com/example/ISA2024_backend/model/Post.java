package com.example.ISA2024_backend.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.ISA2024_backend.model.Comment;

@Entity
@Cacheable
@Table(name = "posts")
public class Post{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false)
	private User owner;
	
    @ElementCollection
    @CollectionTable(name = "likes", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "likes")
	private List<String> likes;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "comments", joinColumns = @JoinColumn(name = "post_id"))
	private List<Comment> comments;
	
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

	public Post(User owner, String imagePath, String content, Float location_x, Float location_y) {
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
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
	
	
	
	
	
	
	
	
	
	
}