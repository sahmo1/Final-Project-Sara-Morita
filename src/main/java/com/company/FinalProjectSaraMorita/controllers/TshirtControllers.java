package com.company.FinalProjectSaraMorita.controllers;

import com.company.FinalProjectSaraMorita.models.Tshirt;
import com.company.FinalProjectSaraMorita.repositories.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TshirtControllers {
    @Autowired
    TshirtRepository tshirtRepository;

    @GetMapping("/tshirts")
    public List<Tshirt> getAllTshirts() {
        return tshirtRepository.findAll();
    }

    public Tshirt findTshirtById(@PathVariable int tShirtID){
        Optional<Tshirt> returnValue = tshirtRepository.findById(tShirtID);

        if (returnValue.isPresent()){
            return returnValue.get();
        }
        else{
            return null;
        }
    }

    @GetMapping("/tshirts/size/{size}")
    public List<Tshirt> getTshirtBySize(@PathVariable String size) {
        List<Tshirt> returnVal = tshirtRepository.findTshirtBySize(size);
        if(returnVal.size() > 0) {
            return returnVal;
        }
        else{
            return null;
        }
    }

    @GetMapping("/tshirts/color/{color}")
    public List<Tshirt> getTshirtByColor(@PathVariable String color) {
        List<Tshirt> returnVal = tshirtRepository.findTshirtByColor(color);
        if(returnVal.size() > 0) {
            return returnVal;
        }
        else {
            return null;
        }
    }

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@RequestBody Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody Tshirt tshirt) {
        tshirtRepository.save(tshirt);
    }

    @DeleteMapping("/tshirts/{}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int ID) {
        tshirtRepository.deleteById(ID);
    }
}
