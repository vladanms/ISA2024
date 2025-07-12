package com.example.ISA2024_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ISA2024_backend.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List <User> findAll();
	User findByEmail(String email);
	User findByUsername(String username);

}
