package service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import model.Post;
import model.User;
import repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository users;
	
	public String register(User user) throws MessagingException, UnsupportedEncodingException {
		User toRegister = users.findByEmail(user.getEmail());
		if(toRegister != null) {
			return "emailError";
		}
		toRegister = users.findByUsername(user.getUsername());
		if(toRegister != null) {
			return "usernameError";
		}
		users.save(user);
		return "success";
	}
	
	public String login(String credentials, String password)	
	{
		User toLogin = users.findByUsername(credentials);
		/*if(toLogin.isAuthorized() == false)
		{
			return null;
		}*/
		if(toLogin != null)
		{
			if(toLogin.getPassword() == password)
			{
				return "username";
			}
		}
		toLogin = users.findByEmail(credentials);
		/*if(toLogin.isAuthorized() == false)
		{
			return null;
		}*/
		if(toLogin != null)
		{
			if(toLogin.getPassword() == password)
			{
				return "email";
			}
		}
		return null;
	}
	
	public User getByUsername(String username) 
	{
		return users.findByUsername(username);
	}
	
	public User getByEmail(String email) 
	{
		return users.findByEmail(email);
	}
	
	public ArrayList<Post> getPosts(String username)
	{
		return users.findByUsername(username).getPosts();
	}
	
	public String generateVerification() 
	 { 

	  String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789"; 
	  StringBuilder sb = new StringBuilder(19); 
	 
	  for (int i = 0; i < 19; i++) { 

	   int index = (int)(characters.length() * Math.random()); 

	   sb.append(characters.charAt(index)); 
	  }  
	  return sb.toString(); 
	 }
	
	public boolean CreatePost(User user, String content, String image)
	{
		Post newPost = new Post(user.getUsername(), image, content);
		ArrayList<Post> posts = user.getPosts();
		posts.add(newPost);
		user.setPosts(posts);
		users.save(user);
		
		return true;
	}
	
	public boolean DeletePost(User user, LocalDateTime time)
	{
		ArrayList<Post> posts = user.getPosts();
		for(Post p : user.getPosts())
		{
			if(p.getTime() == time)
			{
				posts.remove(p);
				user.setPosts(posts);
			}
		}
		users.save(user);
		return true;
	}
	
	public ArrayList<Post> getPostsByUser(User user)
	{
		return user.getPosts();
	}
	
	public ArrayList<Post> getAllPosts()
	{
		ArrayList<Post> posts = new ArrayList<Post>();
		for(User u : users.findAll())
		{
			posts.addAll(u.getPosts());
		}
		return posts;
	}	
}
