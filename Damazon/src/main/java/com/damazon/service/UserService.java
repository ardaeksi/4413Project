package com.damazon.service;

import com.damazon.dto.AuthenticationResponse;
import com.damazon.model.User;
import com.damazon.repository.UserRepository;
import com.damazon.security.JwtTokenProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.damazon.dto.*;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    @Lazy
    private JwtTokenProvider jwtTokenProvider;
       
    // Load user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),"", Collections.singletonList(new SimpleGrantedAuthority(user.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER")));
    }

    // Register a new user
    public User registerNewUser(String username, String password, boolean isAdmin) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Error: Username is already taken!");
        }
        
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setAdmin(false); //Admin can only be manually added to database
        
        return userRepository.save(newUser); //Adds to database using JPQL
    }
    
    
    //Main authenticator that binds into Jwt Token
    public ResponseEntity<?> authenticateUser(User loginRequest) {
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(loginRequest.getUsername());
            if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        
        String token = jwtTokenProvider.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse(userDetails.getUsername(), userDetails.getAuthorities().toString(), token);
        return ResponseEntity.ok(response);
    }
    
    @Transactional
    public ResponseEntity<?> registerUser(LoginRequest newUser) {
        if (userRepository.existsByUsername(newUser.getUserName())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = new User();
        user.setPassword(newUser.getPassword());
        user.setAdmin(false);
        user.setUsername(newUser.getUserName());
        userRepository.save(user);

        return ResponseEntity.ok("User registered");
    }

    
    //Admin method
    public List<User> getAllUsers(){
    	return userRepository.findAll();
    }
    
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        // Attempt to find the existing user by ID
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found for id: " + id);
        }

        User existingUser = userOptional.get();

        
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
      
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getWalletId() != null) {
        	existingUser.setWalletId(updatedUser.getWalletId());
        }
        if (updatedUser.isAdmin() != existingUser.isAdmin()) {
        	existingUser.setAdmin(updatedUser.isAdmin());
        }
        // Saves the updated user
        return userRepository.save(existingUser);
    }
    
    
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }
    
    
    
    
}
