package com.example.springzecoproject;

import com.example.springzecoproject.model.User;
import com.example.springzecoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public String register(User user) {
        // Add your registration logic here
        // For example, save the user to the UserRepository
        userRepository.save(user);

        return "User registered successfully";
    }

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        // Add your authentication logic here
        // For example, verify the password against the stored hashed password

        // Return a JWT token or any other authentication result
        return "Authentication successful";
    }
}




//
//package com.example.springzecoproject;
//
//import com.example.springzecoproject.model.User;
//import com.example.springzecoproject.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class AuthService implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthService(@Lazy UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//    }
//
//    public String register(User user) {
//        if (userRepository.existsByUsername(user.getUsername())) {
//            return "Username is already taken";
//        }
//
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
//
//        return "User registered successfully";
//    }
//
//    public String authenticate(String username, String password) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
//
//        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());
//        if (!passwordMatch) {
//            throw new UsernameNotFoundException("Invalid username or password");
//        }
//
//        // Generate and return a JWT token or any other authentication result
//        return "Authentication successful";
//    }
//}
