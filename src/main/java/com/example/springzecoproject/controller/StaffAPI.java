package com.example.springzecoproject.controller;

import com.example.springzecoproject.model.Staff;
import com.example.springzecoproject.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "http://localhost:3000/")
public class StaffAPI {
    @Autowired
    public StaffRepo staffRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addStaff(@RequestBody Staff staff){
        try {
            Staff staff1 = staffRepo.save(staff);
            return new ResponseEntity<>(staff1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStaff(){
        try {
            List<Staff> staffList = staffRepo.findAll();
            if (staffList.isEmpty()){
                return new ResponseEntity<>("No Staff Found",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(staffList,HttpStatus.OK);
            }

        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId{staffID}")
    public ResponseEntity<?> getByID(@PathVariable int staffID){
        try {
            Optional<Staff> optionalStaff = staffRepo.findById(staffID);

            if (optionalStaff.isPresent()){
                return new ResponseEntity<>(optionalStaff,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Staff Not Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update{staffID}")
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff, @PathVariable int staffID){
        try {
            if (staffRepo.findById(staffID).isPresent()){
                staff.setStaffID(staffID);
                Staff staff1 = staffRepo.save(staff);
                return new ResponseEntity<>(staff1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Staff is not Availbale",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete{staffID}")
    public ResponseEntity<?> deleteStaff(@PathVariable int staffID){
        try {
            staffRepo.deleteById(staffID);
            return new ResponseEntity<>("Deleted successfull",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something wrong",HttpStatus.BAD_REQUEST);
        }
    }

}
