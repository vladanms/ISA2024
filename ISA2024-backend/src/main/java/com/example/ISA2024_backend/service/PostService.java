package com.example.ISA2024_backend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2024_backend.model.Post;
import com.example.ISA2024_backend.model.User;
import com.example.ISA2024_backend.model.Comment;
import com.example.ISA2024_backend.repository.PostRepository;
import com.example.ISA2024_backend.repository.UserRepository;

@Service
public class PostService {

	private PostRepository posts;
	
	public void CreatePost(User owner, String image, String content, Float location_x, Float location_y)
	{
		Post newPost = new Post(owner, image, content, location_x, location_y);
		posts.save(newPost);
	}
	
	public void DeletePost(Post post)
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
	
	public String saveImageToLocalStorage(String localDirectory, File originalImage) throws IOException {

		/*MultipartFile image = new MockMultipartFile();
		
        Path uploadPath = Path.of(localDirectory);
        Path filePath = uploadPath.resolve(image.getOriginalFilename());

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();*/
		
	    InputStream is = null;
	    OutputStream os = null;
		 try {
		is = new FileInputStream(originalImage);
        os = new FileOutputStream(localDirectory+File.separator+generateRandomName());
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
		 } finally {
			 is.close();
			 os.close();
		 }
		
		/*try {
		    Files.copy(originalImage.toPath(), Path.of(localDirectory+File.separator+originalImage.getName()), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		    e.printStackTrace();
		}*/
		
		return localDirectory+File.separator+generateRandomName();
    }
	
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
	
	public String generateRandomName() 
	 { 
	StringBuilder sb = new StringBuilder(24);
	 
	  String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789"; 
	  sb = new StringBuilder(19); 
	 
	  for (int i = 0; i < 19; i++)
	  { 

	   int index = (int)(characters.length() * Math.random()); 
	  
	   sb.append(characters.charAt(index)); 
	  }
	  return sb.toString(); 
	 }
	 
}
