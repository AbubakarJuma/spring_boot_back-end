package com.example.springzecoproject.controller;

import com.example.springzecoproject.model.Login;
import com.example.springzecoproject.repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000/")
public class LoginAPI {

    @Autowired
    public LoginRepo loginRepo;

    @GetMapping("/byId/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username){
        try {
            Optional<Login> optional = loginRepo.findById(username);

            if(optional.isPresent()){
                return  new ResponseEntity<>(optional, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User not ",HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex){
            return new ResponseEntity<>("Network Erro",HttpStatus.BAD_REQUEST);
        }
    }

}
