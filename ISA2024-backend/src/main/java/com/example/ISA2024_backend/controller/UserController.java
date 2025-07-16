package com.example.ISA2024_backend.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.HashSet;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.service.UserService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserService userService;
	private User currentUser;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO){
		
		 Map<String, String> response = new HashMap<>();
		    try {
		if(userService.login(loginDTO) != null)
		{
			currentUser = userService.login(loginDTO);
			response.put("credentials", currentUser.getUsername());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		 response.put("error", "Invalid username or password");
		    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the login request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO userDTO) throws MessagingException, UnsupportedEncodingException
	{
		User user = new User();
		
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setAddress(userDTO.getAddress());
		user.setCity(userDTO.getCity());
		user.setCountry(userDTO.getCountry());
		//user.setPosts(null);
		user.setFollowed(null);
		user.setFollowers(null);
		user.setVerification(userService.generateVerification());
		user.setAuthorized(true);
		
		if(userService.register(user) == "usernameError")
		{
			return new ResponseEntity<>("Username already taken.",HttpStatus.BAD_REQUEST);
		}
		if(userService.register(user) == "emailError")
		{
			return new ResponseEntity<>("Email already taken.",HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Succesfully registered", HttpStatus.OK);
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
