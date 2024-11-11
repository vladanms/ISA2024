package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Post;
import model.User;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	List <Post> findAll();
	List<Post> findByUser(String username);
}
