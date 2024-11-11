package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import model.Post;
import repository.PostRepository;
import repository.UserRepository;

public class PostService {

	@Autowired
	private PostRepository posts;
	
	public void CreatePost(String owner, String content, String image)
	{
		Post newPost = new Post(owner, image, content);
		posts.save(newPost);
	}
	
	public void DeletePost(Post post)
	{
		posts.delete(post);
	}
	
	public ArrayList<Post> getPostsByUser(String owner)
	{
		return (ArrayList<Post>) posts.findByUser(owner);
	}
	
	public ArrayList<Post> getAllPosts()
	{
		return (ArrayList<Post>) posts.findAll();
	}	
}
