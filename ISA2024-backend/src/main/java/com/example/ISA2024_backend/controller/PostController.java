package com.example.ISA2024_backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2024_backend.dto.CreatePostDTO;
import com.example.ISA2024_backend.dto.PostDTO;
import com.example.ISA2024_backend.model.Comment;
import com.example.ISA2024_backend.model.Post;
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.service.PostService;
import com.example.ISA2024_backend.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "post")
@Tag(name = "Post Controller", description = "All post-related operations")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	
	@Operation(summary = "Create post", description = "Create a new post.", responses = 
	 		{
	            @ApiResponse(responseCode = "200", description = "Success"),
	            @ApiResponse(responseCode = "400", description = "Invalid image format"),
	            @ApiResponse(responseCode = "403", description = "User not authorized"),
	            @ApiResponse(responseCode = "500", description = "Internal server error")
	        })
	@PostMapping("/createPost")
	public ResponseEntity<Map<String, String>> createPost(@ModelAttribute CreatePostDTO postDTO) throws MessagingException, IOException
	{
		User owner = userService.getByUsername(postDTO.getOwner());
		Map<String, String> response = new HashMap<>();
		
        try {
            if(postService.createPost(owner, postDTO.getImage(), postDTO.getContent(), postDTO.getLocation_x(),
            		postDTO.getLocation_y()))
            {
            	response.put("success", "Post created");
            	return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else 
            	 response.put("error", "Invalid file type!");
            	 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            
        } catch (Exception e) {
	        e.printStackTrace();
	        response.put("error", "An error occurred while processing the post request.");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@Operation(summary = "Get posts", description = "Retrieve all posts, sorted by time descending.", responses = 
 		{
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
	@GetMapping("/getAllPosts")
	 public ResponseEntity<List<PostDTO>> getAllPosts()
	{ 
		try {			
			List<PostDTO> posts = new ArrayList<>();
			for (Post post : postService.getPostsSortedByTime()) {
			    PostDTO dto = new PostDTO(
			        post.getOwner().getUsername(),
			        post.getLikes(),
			        post.getComments(),
			        post.getImagePath(),
			        post.getTime(),
			        post.getContent(),
			        post.getLocation_x(),
			        post.getLocation_y()
			    );	        
			    posts.add(dto);
			    postService.cacheImage(post);
			}
			return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}	
	}
	
	@Operation(summary = "Comment", description = "Mock comment function for testing authorization and rate limiter.", responses = 
 		{
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "User not authorized"),
            @ApiResponse(responseCode = "429", description = "Too many attempts")
        })
	@PostMapping("/comment")
	public ResponseEntity<Map <String, String>> comment()
	{
		Map<String, String> response = new HashMap<>();
		 String limiter = postService.rateLimiter();
		  if (!limiter.equals("ok")) {
		            response.put("error", ("You're doing that too often. Please try again in " + limiter));
		            return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
		        }
		response.put("success", "You left a comment!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Like", description = "Mock like function for testing authorization and rate limiter.", responses = 
 		{
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "User not authorized"),
            @ApiResponse(responseCode = "429", description = "Too many attempts")
        })
	@PostMapping("/like")
	public ResponseEntity<Map <String, String>> like()
	{
		Map<String, String> response = new HashMap<>();
		 String limiter = postService.rateLimiter();
		  if (!limiter.equals("ok")) {
		            response.put("error", ("You're doing that too often. Please try again in " + limiter));
		            return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
		        }
		response.put("success", "You liked a post!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
