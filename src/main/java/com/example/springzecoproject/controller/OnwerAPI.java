package com.example.springzecoproject.controller;

import com.example.springzecoproject.model.Owner;
import com.example.springzecoproject.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "http://localhost:3000/")
public class OnwerAPI {
    @Autowired
    public OwnerRepo ownerRepo;

    @GetMapping("/all")
    public ResponseEntity<?> getAllOwners(){
        try {
            List<Owner> ownerList = ownerRepo.findAll();
            return new ResponseEntity<>(ownerList, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("No data found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addowner(@RequestBody Owner owner){
        try {
            Owner owner1 = ownerRepo.save(owner);
            return new ResponseEntity<>(owner1,HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId{ownerID}")
    public ResponseEntity<?> ownerbyID(@PathVariable int ownerID){
        try {
            Optional<Owner>  optionalOwner = ownerRepo.findById(ownerID);
            if (optionalOwner.isPresent()){
                return new ResponseEntity<>(optionalOwner.get(),HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Data not Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{ownerID}")
    public ResponseEntity<?> deleteOwner(@PathVariable int ownerID){
        try {
            ownerRepo.deleteById(ownerID);
            return new ResponseEntity<>("Onwer has been deleted successfull",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{ownerID}")
    public ResponseEntity<?> updateOwner(@PathVariable int ownerID,@RequestBody Owner owner){
        try {
            if (ownerRepo.findById(ownerID).isPresent()){
                owner.setOwnerID(ownerID);
                Owner owner1 = ownerRepo.save(owner);
                return new ResponseEntity<>(owner1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Owner not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something  went wrong",HttpStatus.BAD_REQUEST);
        }
    }

}
