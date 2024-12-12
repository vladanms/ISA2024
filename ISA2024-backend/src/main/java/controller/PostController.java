package controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import dto.CreatePostDTO;
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
	
	private String localDirectory = "/images";
	
	@PostMapping("/createPost")
	public ResponseEntity<String> createPost(@RequestBody CreatePostDTO postDTO) throws MessagingException, IOException
	{
		
		postService.CreatePost(postDTO.getOwner(), postService.saveImageToLocalStorage(localDirectory, postDTO.getImage()), postDTO.getContent(), postDTO.getLocation_x(),
				postDTO.getLocation_y());
		return new ResponseEntity<>("Posted!", HttpStatus.OK);
	}
	
	@GetMapping("/getPostsByUser")
	public @ResponseBody ArrayList<Post> getPostsByUser(@Param("username") String username)
	{ 
		return postService.getPostsByUser(username);
	}
	
	@GetMapping("/getAllPosts")
	 public @ResponseBody ArrayList<Post> getAllPosts()
	{ 
		return postService.getAllPosts(); 
	}
	
	@PostMapping("/comment")
    public @ResponseBody void comment(@Param("Id") Long id, @Param("Commenter") String commenter, @Param("Content") String content)
	{
		postService.addComment(postService.getById(id), commenter, content);
	}
	
	@PostMapping("/like")
    public @ResponseBody void comment(@Param("Id") Long id, @Param("Username") String username)
	{
		postService.like(postService.getById(id), username);
	}
	
	@ResponseBody
	@GetMapping(value = "/images/{path}", produces = MediaType.IMAGE_JPEG_VALUE)
	@Cacheable("images")
	public byte[] cacheImage(@PathVariable String path) throws IOException {
        URL url = new URL("/images"+path);
        InputStream in = new BufferedInputStream(url.openStream());
        return FileCopyUtils.copyToByteArray(in); 
	}
}
