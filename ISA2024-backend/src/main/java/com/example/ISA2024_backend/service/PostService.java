package com.example.ISA2024_backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ISA2024_backend.model.Post;
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.repository.PostRepository;

import org.springframework.cache.CacheManager;

@Service
public class PostService {

	@Value("${image.directory:images}")
	private String imageDirectory;
	private final Map<String, List<Long>> globalActions = new ConcurrentHashMap<>();

    @Autowired
    private CacheManager cacheManager;
	
	@Autowired
	private PostRepository posts;

	public Boolean createPost(User owner, MultipartFile image, String content, Float location_x, Float location_y)  throws IOException
	{
		Post newPost = new Post(owner, "", content, location_x, location_y);
		newPost = posts.save(newPost);
		
	    String format = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
	    if(!format.equals(".jpg") && !format.equals(".png"))
	    {
		posts.delete(newPost);
		return false;
	    }
		Files.copy(image.getInputStream(), Paths.get(imageDirectory, newPost.getId() + format), StandardCopyOption.REPLACE_EXISTING);
		String imagePath = (Paths.get(imageDirectory, newPost.getId() + format)).toString();
		newPost.setImagePath(imagePath);
		
		Float[] location = new Float[]{location_x, location_y};
        cacheManager.getCache("postLocationCache").put(newPost.getId(), location);
        
       /* Float[] locationCheck = cacheManager.getCache("postLocationCache").get(newPost.getId(), Float[].class);
        System.out.println("Cache check!" + locationCheck);*/
        
		posts.save(newPost);
		return true;
	}
	
	public void cacheImage(Post post) throws IOException
	{
		byte[] imageBytes = Files.readAllBytes(Path.of(post.getImagePath()));
		cacheManager.getCache("postImageCache").put(post.getId(), imageBytes);
		
		/*S
		byte[] imageCheck = cacheManager.getCache("postImageCache").get(post.getId(), byte[].class);
		System.out.println("Cache check!");
		System.out.println(imageCheck.toString());
		 */
	}
	
	
	public void deletePost(Post post)
	{
		posts.delete(post);
	}
	
	public ArrayList<Post> getPostsByUser(String owner)
	{
		return (ArrayList<Post>) posts.findByOwner(owner);
	}
	
	public List<Post> getPostsSortedByTime()
	{
		return (List<Post>) posts.findAllByOrderByTimeDesc();
	}
	
	public List<Post> getAllPosts()
	{
		return (List<Post>) posts.findAll();
	}	
	
	 
	public synchronized String rateLimiter() 
	{
		 
		 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    long currentTime = System.currentTimeMillis();
		    List<Long> actions = globalActions.getOrDefault(authentication.getName(), new ArrayList<>());
		    actions.removeIf(attemptTime -> currentTime - attemptTime > TimeUnit.MINUTES.toMillis(1));

		    if (actions.size() >= 5) {
		    	return TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MINUTES.toMillis(1) - (currentTime - actions.get(0))) + " minutes and " +  
		    			TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MINUTES.toMillis(1) - (currentTime - actions.get(0))) % 60 + " seconds.";
		    }
		    actions.add(currentTime);
		    globalActions.put(authentication.getName(), (actions));
		    return "ok";
	}
	
	
}
