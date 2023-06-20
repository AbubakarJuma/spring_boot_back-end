package com.example.springzecoproject.controller;


import com.example.springzecoproject.model.Building;
import com.example.springzecoproject.repository.BuildingRepo;
import com.example.springzecoproject.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/building")
@CrossOrigin(origins = "http://localhost:3000/")
public class BuildingAPI {
    @Autowired
    public BuildingRepo buildingRepo;


    @GetMapping("/all")
    public ResponseEntity<?> getbuildings(){
        try {
            List<Building> buildingList = buildingRepo.findAll();
            if (buildingList.isEmpty()){
                return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(buildingList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId/{buildID}")
    public ResponseEntity<?> getBuildByID(@PathVariable int buildID){
        try {
            Optional<Building> buildingOptional = buildingRepo.findById(buildID);

            if (buildingOptional.isPresent()){
                return new ResponseEntity<>(buildingOptional,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No Data Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBuilding(@RequestBody Building building){
        try {
            Building building1 = buildingRepo.save(building);
            return new ResponseEntity<>(building1,HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{buildID}")
    public ResponseEntity<?> updateBuilding(@PathVariable int buildID, @RequestBody Building building){
        try {
            if (buildingRepo.findById(buildID).isPresent()){
                building.setBuildID(buildID);
                Building building1 = buildingRepo.save(building);
                return new ResponseEntity<>(building1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Data not Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{buildID}")
    public ResponseEntity<?> deleteBuild(@PathVariable int buildID){
        try {
            buildingRepo.deleteById(buildID);
            return new ResponseEntity<>("Deleted successfull",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Data not found",HttpStatus.NOT_FOUND);
        }
    }
}
