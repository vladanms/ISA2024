package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.UserRegistered;
import model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List <User> findAll();
	User findByEmail(String email);
	User findByUsername(String username);
	User findByVerification(String verification);

}
