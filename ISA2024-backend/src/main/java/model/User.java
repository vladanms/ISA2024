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
	
	@Column(name = "lastLogged", nullable = false)
	private LocalDateTime lastLogged;
	
	private boolean authorized;
	
	//@OneToMany(fetch = FetchType.EAGER)
	private ArrayList<String> followers;
	
	//@ManyToOne(fetch = FetchType.EAGER)
	private ArrayList<String> followed;
	
	//@OneToMany(targetEntity = Post.class, fetch = FetchType.EAGER)
	//private ArrayList<Post> posts;

	
	
	public User() {
		super();
	}

	public User(String username, String password, String email, String name, String surname, String address,
			String city, String country, String verification, ArrayList<String> followers, ArrayList<String> followed, LocalDateTime lastLoggedd) {
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
		//this.posts = posts;
		this.authorized = false;
		this.lastLogged = lastLogged;
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

	public ArrayList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<String> followers) {
		this.followers = followers;
	}

	public ArrayList<String> getFollowed() {
		return followed;
	}

	public void setFollowed(ArrayList<String> followed) {
		this.followed = followed;
	}

	/*public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}*/

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public LocalDateTime getLastLogged() {
		return lastLogged;
	}

	public void setLastLogged(LocalDateTime lastLogged) {
		this.lastLogged = lastLogged;
	}
	
	
	
	
	
	
}
