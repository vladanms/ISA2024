package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Post;
import model.User;
import model.Comment;
import repository.PostRepository;
import repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository posts;
	@Autowired
	private UserRepository users;
	
	public void CreatePost(String owner, String image, String content, Float location_x, Float location_y)
	{
		Post newPost = new Post(owner, image, content, location_x, location_y);
		posts.save(newPost);
	}
	
	
	
	public void DeletePost(Post post)
	{
		posts.delete(post);
	}
	
	
	
	public ArrayList<Post> getPostsByUser(String owner)
	{
		return (ArrayList<Post>) posts.findByOwner(owner);
	}
	
	
	
	public ArrayList<Post> getAllPosts()
	{
		return (ArrayList<Post>) posts.findAll();
	}	
	
	
	public Post getById(Long id)
	{
		return posts.findById(id).get();
	}
	
	
	public ArrayList<Post> getFollowedPosts(String username)
	{
		ArrayList<Post> ret = new ArrayList<Post>();
		User user = users.findByUsername(username);
			for(String followed : user.getFollowed())
			{
				ret.addAll(posts.findByOwner(username));
			}
		sortByTime(ret);
		return ret;
	}
	
	
	
    public void sortByTime(ArrayList<Post> list) {    	 
        list.sort((o1, o2)
                  -> o1.getTime().compareTo(
                      o2.getTime()));
    }
    
    
    public void addComment(Post post, String commenter, String content)
    {
    	ArrayList<Comment> comments = post.getComments();
    	comments.add(new Comment(commenter, content));
    	post.setComments(comments);
    	posts.save(post);
    }
    
    
    public void like(Post post, String username)
    {
    	ArrayList<String> likes = post.getLikes();
    	boolean liked = false;
    	for(String like : likes)
    	{
    		if(like.equals(username));
    		liked = true;
    	}
    	if(!liked)
    	{
    		likes.add(username);
    	}
    	else
    	{
    		likes.remove(username);
    	}
    	post.setLikes(likes);
    	posts.save(post);
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
