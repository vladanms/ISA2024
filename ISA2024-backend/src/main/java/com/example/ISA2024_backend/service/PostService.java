package com.example.ISA2024_backend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ISA2024_backend.model.Post;
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.model.Comment;
import com.example.ISA2024_backend.repository.PostRepository;
import com.example.ISA2024_backend.repository.UserRepository;

@Service
public class PostService {

	 @Value("${image.directory:images}")
	 private String imageDirectory;

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
		posts.save(newPost);
		return true;
	}
	
	public void deletePost(Post post)
	{
		posts.delete(post);
	}
	
	public void Comment(Post post)
	{
		//this function does nothing, it's to be called only for testing purposes.
	}
	
	public ArrayList<Post> getPostsByUser(String owner)
	{
		return (ArrayList<Post>) posts.findByOwner(owner);
	}
	
	public ArrayList<Post> getAllPosts()
	{
		return (ArrayList<Post>) posts.findAll();
	}	
	
	/*public Boolean copyImage(String name, MultipartFile imageFile) throws IOException {
		
	    String format = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));
	    if(!format.equals(".jpg") && !format.equals(".png"))
	    {
	    	return false;
	    }

		 
		Files.copy(imageFile.getInputStream(), Paths.get(imageDirectory, name + format), StandardCopyOption.REPLACE_EXISTING);
		return true;
    }*/
	
	public String getAbsolutePath(String path)
	{
		File finalImage = new File(path);
		return finalImage.getAbsolutePath();
    }
	
	
	public byte[] getImage(String localDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(localDirectory, imageName);

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null;
        }
	}
	 
}
