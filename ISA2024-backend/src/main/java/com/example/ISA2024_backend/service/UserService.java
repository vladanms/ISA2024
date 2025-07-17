package com.example.ISA2024_backend.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISA2024_backend.dto.LoginDTO;
import com.example.ISA2024_backend.model.Post;
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.repository.UserRepository;

import main.model.UserRegistered;

@Service
public class UserService {

	@Autowired
	private UserRepository users;
	private JavaMailSender sender;
 //   private final PasswordEncoder passwordEncoder;
	
/*    @Autowired
    public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }*/
	
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
	
	public User login(LoginDTO loginDTO)	
	{
		User toLogin = users.findByUsername(loginDTO.getCredentials());
		if(toLogin != null)
		{
			if(toLogin.getPassword().equals(toLogin.getPassword()))
			{
				return users.findByUsername(loginDTO.getCredentials());
			}
		}
		toLogin = users.findByEmail(loginDTO.getCredentials());
		if(toLogin != null)
		{
			if(toLogin.getPassword().equals(toLogin.getPassword()))
			{
				return users.findByEmail(loginDTO.getCredentials());
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
	
	public List<Post> getPosts(String username)
	{
		return users.findByUsername(username).getPosts();
	}
	
	public String generateVerification() 
	 { 

	  String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789"; 
	  StringBuilder sb = new StringBuilder(20); 
	 
	  for (int i = 0; i < 19; i++) { 

	   int index = (int)(characters.length() * Math.random()); 

	   sb.append(characters.charAt(index)); 
	  }  
	  return sb.toString(); 
	 }
	
	public void verificationEmail(User user) throws MessagingException, UnsupportedEncodingException 
	{
	    String toAddress = user.getEmail();
	    String fromAddress = "OnlyBuns verification";
	    String senderName = "OnlyBuns";
	    String subject = "Verification";
	    String content = "[[user]],<br>"
	            + "You're one step away from copleting your registration. To finalize, please open the following link:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>";
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[user]]", user.getUsername());
	    content = content.replace("[[URL]]", "http://localhost:8091/user/verify?username=" + user.getUsername() +"&code=" + user.getVerification());
	     
	    helper.setText(content, true);
	     
	    sender.send(message);
	}
	
	public boolean verification(String username, String verification)
	{
		User user = (User) users.findByUsername(username);
		if(user!= null)
		{
			if(user.getVerification().equals(verification))
			{
					user.setAuthorized(true);
					user.setVerification(null);
					users.save(user);
					return true;
			}	
			return false;
		}
		return false;
	}
	
/*	public boolean CreatePost(User user, String content, String image)
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
	}	*/
}
