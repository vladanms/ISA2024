package com.example.ISA2024_backend.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "verification", nullable = false)
	private String verification;
	
	@Column(name = "authorized")
	private boolean authorized;
	
	@Column(name = "lastActive")
	private LocalDateTime lastActive;
	
	@ElementCollection
	@CollectionTable(name = "followers", joinColumns = @javax.persistence.JoinColumn(name = "user_id"))
	@Column(name = "followers")
	private List<String> followers;
	
	@ElementCollection
	@CollectionTable(name = "followed", joinColumns = @javax.persistence.JoinColumn(name = "user_id"))
	@Column(name = "followed")
	private List<String> followed;
	
	@OneToMany(targetEntity = Post.class, mappedBy = "owner", fetch = FetchType.EAGER)
	private List<Post> posts;

	
	
	public User() {
		super();
	}

	public User(String username, String password, String email, String name, String surname, String address,
			String city, String country, String verification, List<Post> posts, List<String> followers, List<String> followed) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.verification = verification;
		this.followers = followers;
		this.followed = followed;
		this.posts = posts;
		this.authorized = false;
		this.lastActive = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public List<String> getFollowed() {
		return followed;
	}

	public void setFollowed(List<String> followed) {
		this.followed = followed;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public boolean getAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public LocalDateTime getLastActive() {
		return lastActive;
	}

	public void setLastActive(LocalDateTime lastActive) {
		this.lastActive = lastActive;
	}
	
	
	
	
	
}
