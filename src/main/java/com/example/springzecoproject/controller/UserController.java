package com.example.springzecoproject.controller;

import com.example.springzecoproject.AuthService;
import com.example.springzecoproject.component.LoginRequest;
import com.example.springzecoproject.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String message = authService.register(user);
        return ResponseEntity.ok(message);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
//        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//        return ResponseEntity.ok(token);
//    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}





//package com.example.springzecoproject.controller;
//
//import com.example.springzecoproject.AuthService;
//import com.example.springzecoproject.component.LoginRequest;
//import com.example.springzecoproject.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "*")
//public class UserController {
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        String message = authService.register(user);
//        return ResponseEntity.ok(message);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
//        String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//        return ResponseEntity.ok(token);
//    }
//}
