package com.example.ISA2024_backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ISA2024_backend.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List <Post> findAll();
	List<Post> findByOwner(String username);
	List<Post> findAllByOrderByTimeDesc(Pageable pageable);
}
