package com.example.ISA2024_backend.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.HashSet;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2024_backend.dto.LoginDTO;
import com.example.ISA2024_backend.dto.RegisterDTO;
import com.example.ISA2024_backend.model.Post;
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.service.UserService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "user")
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private User currentUser;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
		
		 Map<String, String> response = new HashMap<>();
		 String ip = request.getRemoteAddr();
		 String limiter = userService.loginLimiter(ip);
		 try {
		  if (!limiter.equals("ok")) {
		            response.put("error", ("You're doing that too often. Please try again in " + limiter));
		            return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
		        }
		  Authentication auth = authenticationManager.authenticate(
		            new UsernamePasswordAuthenticationToken(loginDTO.getCredentials(), loginDTO.getPassword())
		        );
		  		SecurityContextHolder.getContext().setAuthentication(auth);
		        response.put("credentials", loginDTO.getCredentials());
		        return new ResponseEntity<>(response, HttpStatus.OK);
		 } 
		 catch (AuthenticationException e) {
		 response.put("error", "Invalid username or password.");
		    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		    } 
		 catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the login request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(@RequestBody String username){
		
		 Map<String, String> response = new HashMap<>();
		    try {
		        if (currentUser == null) {
		            response.put("error", "No user is logged in.");
		            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		        }
		    	if(currentUser.getUsername().equals(username))
		    	{
		    	response.put("username", username);
		    	currentUser = null;
			    return new ResponseEntity<>(response, HttpStatus.OK);
		    	}
		    	response.put("error", "Username mismatch error.");
		        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the logout request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO userDTO) throws MessagingException, UnsupportedEncodingException
	{
		
	    List<String> followers = new ArrayList<>();
	    List<String> followed = new ArrayList<>();
	    List<Post> posts = new ArrayList<>(); 
	    
		User user = new User();
		
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword())); 
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setAddress(userDTO.getAddress());
		user.setCity(userDTO.getCity());
		user.setCountry(userDTO.getCountry());
		user.setPosts(posts);
		user.setFollowed(followed);
		user.setFollowers(followers);
		user.setVerification(userService.generateVerification());
		user.setAuthorized(true);
		
		Map<String, String> response = new HashMap<>();
		
		try {
			String register = userService.register(user);
		if(register.equals("usernameError"))
		{
			response.put("error", "Username taken. Please select another one.");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(register.equals("emailError"))
		{
			response.put("error", "This e-mail is already registered.");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		//userService.verificationEmail(user);
		response.put("registered", userDTO.getUsername());
		return new ResponseEntity<>(response, HttpStatus.OK);
		 } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the registration request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@GetMapping("/verify")
	public boolean verifyUser(@Param("username") String username, @Param("code") String code) {
	    if (userService.verification(username, code)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
/*	@PutMapping("/createPost")
	public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) throws MessagingException, UnsupportedEncodingException
	{
		
		userService.CreatePost(currentUser, postDTO.getImagePath(), postDTO.getContent());
		return new ResponseEntity<>("Succesfully registered", HttpStatus.OK);
	}
	
	@GetMapping("/getPosts")
	 public @ResponseBody ArrayList<Post> getPostsByUser(String username)
	{ 
		return userService.getByUsername(username).getPosts(); 
	}
	
	@GetMapping("/getAllPosts")
	 public @ResponseBody ArrayList<Post> getAllPosts()
	{ 
		return userService.getAllPosts(); 
	}
	*/
}
