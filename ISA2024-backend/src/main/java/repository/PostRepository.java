package repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Post;
import model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findAll();
	List<Post> findByOwner(String owner);
}
