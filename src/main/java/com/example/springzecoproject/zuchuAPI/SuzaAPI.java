package com.example.springzecoproject.zuchuAPI;

import com.example.springzecoproject.model.Suza;
import com.example.springzecoproject.repository.SuzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zuchu")
public class SuzaAPI {
    @Autowired
    private SuzaRepo suzaRepo;


    @GetMapping("/all")
    public ResponseEntity<?> getSuza(){
        try {
            List<Suza> suzaList = suzaRepo.findAll();
            if (suzaList.isEmpty()){
                return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(suzaList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Network Porject",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/byId{suzaID}")
    public ResponseEntity<?> getByID(@PathVariable int suzaID){
        try {
            Optional<Suza> optionalSuza = suzaRepo.findById(suzaID);

            if(optionalSuza.isPresent()){
                return new ResponseEntity<>(optionalSuza,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Hakuna Data hio",HttpStatus.NOT_FOUND);

            }
        }catch (Exception pandu){
            return new ResponseEntity<>("System Down",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")

    public ResponseEntity<?> addMember(@RequestBody Suza suza){
        try {
            Suza suza1 = suzaRepo.save(suza);
            return new ResponseEntity<>("Inserted successfull",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Uzembe wa Developer",HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete{suzaID}")
    public ResponseEntity<?> delete(@PathVariable int suzaID){
        try {
            suzaRepo.deleteById(suzaID);
            return new ResponseEntity<>("Data imefutika",HttpStatus.OK);
        }catch (Exception zuchuuu){
            return new ResponseEntity<>("Server Does not exist",HttpStatus.BAD_REQUEST);
        }
    }











}










