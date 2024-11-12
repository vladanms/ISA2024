package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import jakarta.mail.MessagingException;

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

import dto.LoginDTO;
import dto.PostDTO;
import dto.RegisterDTO;
import dto.UserDTO;
import service.UserService;
import model.Post;
import model.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserService userService;
	private User currentUser;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
		if(userService.login(loginDTO).equals("username")) {
			currentUser = userService.getByUsername(loginDTO.getCredentials());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		if(userService.login(loginDTO) == "email") {
			currentUser = userService.getByEmail(loginDTO.getCredentials());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid username or password",HttpStatus.BAD_REQUEST);
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
