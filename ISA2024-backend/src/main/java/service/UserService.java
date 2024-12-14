package service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import main.model.UserRegistered;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dto.LoginDTO;
import model.Post;
import model.User;
import repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository users;
	@Autowired
	private JavaMailSender sender;
	@Autowired
    private final PasswordEncoder passwordEncoder;
	
    public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }
	
	public String register(User user) throws MessagingException, UnsupportedEncodingException {
		User toRegister = users.findByEmail(user.getEmail());
		if(toRegister != null) {
			return "emailError";
		}
		toRegister = users.findByUsername(user.getUsername());
		if(toRegister != null) {
			return "usernameError";
		}
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.save(user);
		return "success";
	}
	
	public String login(LoginDTO loginDTO)	
	{
		User toLogin = users.findByUsername(loginDTO.getCredentials());
		/*if(toLogin.isAuthorized() == false)
		{
			return null;
		}*/
		if(toLogin != null)
		{
			if(toLogin.getPassword() == loginDTO.getPassword())
			{
				toLogin.setLastLogged(LocalDateTime.now());
				users.save(toLogin);
				return "username";
			}
		}
		toLogin = users.findByEmail(loginDTO.getCredentials());
		/*if(toLogin.isAuthorized() == false)
		{
			return null;
		}*/
		if(toLogin != null)
		{
			if(toLogin.getPassword() == loginDTO.getPassword())
			{
				toLogin.setLastLogged(LocalDateTime.now());
				users.save(toLogin);
				return "email";
			}
		}
		return null;
	}
	
	public Boolean logTime(String username)
	{
		User user;
		if(users.findByUsername(username) != null)
		{
			user = users.findByUsername(username);
		}
		else if(users.findByEmail(username) != null)
		{
			user = users.findByEmail(username);
		}
		else
		{
			return false;
		}
		user.setLastLogged(LocalDateTime.now());
		users.save(user);
		return true;
	}
	
	public User getByUsername(String username) 
	{
		return users.findByUsername(username);
	}
	
	public User getByEmail(String email) 
	{
		return users.findByEmail(email);
	}
	
/*	public ArrayList<Post> getPosts(String username)
	{
		return users.findByUsername(username).getPosts();
	}*/
	
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
	
	public void emailVerification(String email) throws MessagingException, IOException
	{
		MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    
	    helper.setFrom("registration@ISA.com", "ISA project");
	    helper.setTo(email);
	    helper.setSubject("Verify your account");
	    helper.setText("Hello<br><br>,"
	    		+ "To verify your account, click the following link:<br>"
	    		+ "http://localhost:4200/user/verify?verification=" + users.findByEmail(email).getVerification(),
	    		true
	    		);	    
	    sender.send(message);
	}
	
	public boolean verify(String verification) {
	    User user =  users.findByVerification(verification);
	     
	    if (user == null) {
	        return false;
	    } else {
	        user.setVerification("verified");
	        users.save(user);	         
	        return true;
	    }
	     
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
