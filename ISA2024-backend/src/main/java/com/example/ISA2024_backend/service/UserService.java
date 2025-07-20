package com.example.ISA2024_backend.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISA2024_backend.dto.RegisterDTO;
import com.example.ISA2024_backend.model.Post;
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.repository.PostRepository;
import com.example.ISA2024_backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository users;
	
	@Autowired
	private PostRepository posts;
	
	@Autowired
	private JavaMailSender sender;
	private final Map<String, List<Long>> loginAttempts = new ConcurrentHashMap<>();
	
	public String register(RegisterDTO userDTO, PasswordEncoder encoder) throws MessagingException, UnsupportedEncodingException {
		User toRegister = users.findByEmail(userDTO.getEmail());
		if(toRegister != null) {
			return "emailError";
		}
		toRegister = users.findByUsername(userDTO.getUsername());
		if(toRegister != null) {
			return "usernameError";
		}
		
	    List<String> followers = new ArrayList<>();
	    List<String> followed = new ArrayList<>();
	    List<Post> posts = new ArrayList<>(); 
	    
		User user = new User();
		
		user.setUsername(userDTO.getUsername());
		user.setPassword(encoder.encode(userDTO.getPassword())); 
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setAddress(userDTO.getAddress());
		user.setCity(userDTO.getCity());
		user.setCountry(userDTO.getCountry());
		user.setPosts(posts);
		user.setFollowed(followed);
		user.setFollowers(followers);
		user.setVerification(generateVerification());
		user.setAuthorized(true);
		users.save(user);
		return "success";
	}
	
	 @Override
	    public UserDetails loadUserByUsername(String credentials) throws UsernameNotFoundException {
	        User user = users.findByUsername(credentials);
	        
	        if (user == null) {
	            user = users.findByEmail(credentials);
	            if (user == null) {
	                throw new UsernameNotFoundException("User not found!");
	            }
	        }
	        
	        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
	            .password(user.getPassword());

	        
	        if (user.getAuthorized()) {
	            builder.roles("USER_AUTHORIZED");
	        } else {
	            builder.roles("");
	        }

	        return builder.build();
	    }
	 
	 public void setLastActive(String credentials){
		 User user = users.findByUsername(credentials);
		  if (user == null) {
	            user = users.findByEmail(credentials);
	            if (user == null) {
	                throw new UsernameNotFoundException("User not found!");
	            }
		  }
		 if(user != null)
		 {
			 user.setLastActive(LocalDateTime.now());
			 users.save(user);
		 }

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
	
	public void testMail() throws MessagingException, UnsupportedEncodingException 
	{
	    String toAddress = "recipient@gmail.com"; //change this to your e-mail when testing
	    String fromAddress = "sender@gmail.com";  //change this to your e-mail when testing
	    String senderName = "OnlyBuns";
	    String subject = "test";
	    String content = "email service works!";
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    sender.send(message);
	    
	    System.out.println("mail sent!");
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public void reminderEmail() throws MessagingException, UnsupportedEncodingException 
	{
		List<User> userList = users.findAllByLastActiveBefore(LocalDateTime.now().minusDays(7));
		
		for(User user : userList)
		{
		List<Post> nearbyPosts = posts.findAllByOwner_CityAndTimeAfter(user.getCity(), user.getLastActive());
			
	    String toAddress = user.getEmail();
	    String fromAddress = "vladans995@gmail.com";
	    String senderName = "OnlyBuns";
	    String subject = "We miss you!";
	    String content = user.getUsername() + ",<br>"
	            + "You've been away for some time. Since we've seen you last, other bunny lovers from " + user.getCity() + " have made " + nearbyPosts.size() + "new posts."
	            + " Come check them out! <br><br>"
	            + "OnlyBuns team";
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    sender.send(message);
		}
	}
	
	 public synchronized String loginLimiter(String ip) {
	        long currentTime = System.currentTimeMillis();
	        List<Long> attempts = loginAttempts.getOrDefault(ip, new ArrayList<>());

	        attempts.removeIf(attemptTime -> currentTime - attemptTime > TimeUnit.MINUTES.toMillis(5));
	        if (attempts.size() >= 5) {
	            return TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MINUTES.toMillis(5) - (currentTime - attempts.get(0))) + " minutes and " +  
	            		TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MINUTES.toMillis(5) - (currentTime - attempts.get(0))) % 60 + " seconds.";
	        }
	        attempts.add(currentTime);
	        loginAttempts.put(ip, attempts);
	        return "ok";
	    }
}
