package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dto.PostDTO;
import jakarta.mail.MessagingException;
import model.Post;
import service.PostService;
import service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "post")

public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/createPost")
	public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) throws MessagingException, UnsupportedEncodingException
	{
		
		postService.CreatePost(postDTO.getUser(), postDTO.getImagePath(), postDTO.getContent());
		return new ResponseEntity<>("Succesfully registered", HttpStatus.OK);
	}
	
	@GetMapping("/getPosts")
	 public @ResponseBody ArrayList<Post> getPostsByUser(String username)
	{ 
		return postService.getPostsByUser(username);
	}
	
	@GetMapping("/getAllPosts")
	 public @ResponseBody ArrayList<Post> getAllPosts()
	{ 
		return postService.getAllPosts(); 
	}
}
