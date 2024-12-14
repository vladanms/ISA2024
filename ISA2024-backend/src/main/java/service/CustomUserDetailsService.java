package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import model.User;
import repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

	 private final UserRepository users;

	    @Autowired
	    public CustomUserDetailsService(UserRepository userRepository) {
	        this.users = userRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        // Find user by username
	        User user = users.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }

	        // Return a Spring Security User object
	        return org.springframework.security.core.userdetails.User
	            .withUsername(user.getUsername())
	            .password(user.getPassword())
	            .roles("USER")  // Assign roles as needed
	            .build();
	    }
}
