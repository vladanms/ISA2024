package com.example.ISA2024_backend.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.ISA2024_backend.dto.LoginDTO;
import com.example.ISA2024_backend.dto.RegisterDTO;
import com.example.ISA2024_backend.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "user")
@Tag(name = "User Controller", description = "All user-related operations")
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	 @Operation(summary = "Log in", description = "Log in and authorize a user.", responses = 
		 		{
		            @ApiResponse(responseCode = "200", description = "Success"),
		            @ApiResponse(responseCode = "400", description = "Invalid credentials"),
		            @ApiResponse(responseCode = "429", description = "Too many attempts"),
		            @ApiResponse(responseCode = "500", description = "Internal server error")
		        })
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
		
		 Map<String, String> response = new HashMap<>();
		 String ip = request.getRemoteAddr();
		 String limiter = userService.loginLimiter(ip);
		 try {
		  if (!limiter.equals("ok")) {
		            response.put("error", ("You're doing that too often. Please try again in " + limiter));
		            return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
		        }
		  Authentication auth = authenticationManager.authenticate(
		            new UsernamePasswordAuthenticationToken(loginDTO.getCredentials(), loginDTO.getPassword())
		        );
		  		SecurityContextHolder.getContext().setAuthentication(auth);
		        response.put("credentials", loginDTO.getCredentials());
		        userService.setLastActive(loginDTO.getCredentials());
		        return new ResponseEntity<>(response, HttpStatus.OK);
		 } 
		 catch (AuthenticationException e) {
		 response.put("error", "Invalid username or password.");
		    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		    } 
		 catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the login request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@Operation(summary = "Log out", description = "Log out and clear authorization.", responses = 
	 		{
	            @ApiResponse(responseCode = "200", description = "Success"),
	            @ApiResponse(responseCode = "400", description = "Invalid request"),
	            @ApiResponse(responseCode = "500", description = "Internal server error")
	        })
	@PostMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(@RequestBody String username){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Map<String, String> response = new HashMap<>();
		    try {
		        if (authentication == null) {
		            response.put("error", "No user is logged in.");
		            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		        }
		    	if(authentication.getName().equals(username))
		    	{
			    userService.setLastActive(username);
		    	response.put("username", username);
		    	
		        new SecurityContextLogoutHandler().logout(
		        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest(),
		        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse(),
		        authentication
		        );
		        
			    return new ResponseEntity<>(response, HttpStatus.OK);
		    	}
		    	response.put("error", "Username mismatch error.");
		        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the logout request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
    @Operation(summary = "Register a new user", description = "Register a new user with details provided.", responses = 
    		{
                @ApiResponse(responseCode = "200", description = "Success"),
                @ApiResponse(responseCode = "400", description = "Username or email taken"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
            })
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO userDTO) throws MessagingException, UnsupportedEncodingException
	{

		Map<String, String> response = new HashMap<>();
		
		try {
			String register = userService.register(userDTO, passwordEncoder);
		if(register.equals("usernameError"))
		{
			response.put("error", "Username taken. Please select another one.");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(register.equals("emailError"))
		{
			response.put("error", "This e-mail is already registered.");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		response.put("registered", userDTO.getUsername());
		return new ResponseEntity<>(response, HttpStatus.OK);
		 } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the registration request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
}
