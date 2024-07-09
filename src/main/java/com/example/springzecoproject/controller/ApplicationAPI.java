package com.example.springzecoproject.controller;

import com.example.springzecoproject.model.Application;
import com.example.springzecoproject.repository.ApplicationRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application")
// @CrossOrigin(origins = "http://localhost:3000/")
 @CrossOrigin( origins = "https://collegeapplications.vercel.app/")
public class ApplicationAPI {
    @Autowired
    public ApplicationRepo applicationRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addApplication(@RequestBody Application application){
        try {
            Application application1 = applicationRepo.save(application);
            return new ResponseEntity<>(application1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getApplication(){
        try {
            List<Application> applicationList = applicationRepo.findAll();
            if (applicationList.isEmpty()){
                return new ResponseEntity<>("No Application Found",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(applicationList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId{appID}")
    public ResponseEntity<?> getByID(@PathVariable int appID){
        try {
            Optional<Application> optionalApplication = applicationRepo.findById(appID);

            if (optionalApplication.isPresent()){
                return new ResponseEntity<>(optionalApplication,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No Application Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update{appID}")
    public ResponseEntity<?> updateApplication(@RequestBody Application application,@PathVariable int appID){
        try {
            if (applicationRepo.findById(appID).isPresent()){
                application.setAppID(appID);
                Application application1 = applicationRepo.save(application);
                return new ResponseEntity<>(application1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No application found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete{appID}")
    public ResponseEntity<?> deleteApplication(@PathVariable int appID){
        try {
            applicationRepo.deleteById(appID);
            return new ResponseEntity<>("Application Deleted Successfull",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong please wait",HttpStatus.BAD_REQUEST);
        }
    }

}
