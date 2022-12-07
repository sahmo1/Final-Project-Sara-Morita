package com.company.FinalProjectSaraMorita.controllers;

import com.company.FinalProjectSaraMorita.models.Console;
import com.company.FinalProjectSaraMorita.repositories.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping("/consoles")
    public List<Console> getConsoles(){
        return consoleRepository.findAll();

    }

    @GetMapping("/consoles/{id}")
    public Console getConsolesById(@PathVariable int id) {
        Optional<Console> returnValue = consoleRepository.findById(id);
        if(returnValue.isPresent()) {
            return returnValue.get();
        }
        return null;
    }

    @GetMapping("/consoles/manufacturers/{manufacturer}")
    public List<Console>getConsolesByManufacturer(@PathVariable String manufacturer) {
        List<Console> returnValue = consoleRepository.findAllConsolesByManufacturer(manufacturer);
        if(returnValue.size() > 0) {
            return returnValue;
        }
        return null;
    }

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return consoleRepository.save(console);
    }

    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        consoleRepository.save(console);
    }

    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        consoleRepository.deleteById(id);
    }




}
