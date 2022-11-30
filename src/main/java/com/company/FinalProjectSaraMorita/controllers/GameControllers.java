package com.company.FinalProjectSaraMorita.controllers;

import com.company.FinalProjectSaraMorita.models.Game;
import com.company.FinalProjectSaraMorita.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameControllers {

    @Autowired
    GameRepository gameRepo;

    @GetMapping("/games")
    public List<Game> getGames(){
        return gameRepo.findAll();
    }


    @GetMapping("/games/{id}")
    public Game getGamesById(@PathVariable int id){
        Optional<Game> returnValue = gameRepo.findById(id);
        if (returnValue.isPresent()){
            return returnValue.get();
        } else{
            return null;
        }
    }

    @GetMapping("/games/rating/{esrbRating}")
    public List<Game> getGamesByEsrbRating(@PathVariable String esrbRating) {
        List<Game> returnVal = gameRepo.findAllGamesByEsrbRating(esrbRating);
        if (returnVal.size() > 0) {
            return returnVal;
        } else {
            return null;
        }
    }

    @GetMapping("/games/title/{title}")
    public List<Game> getGameByTitle(@PathVariable String title) {
        List<Game> returnVal = gameRepo.findAllGamesByTitle(title);
        if (returnVal.size() > 0) {
            return returnVal;
        } else {
            return null;
        }
    }

    @GetMapping("/games/studio/{studio}")
    public List<Game> getGameByStudio(@PathVariable String studio) {
        List<Game> returnVal = gameRepo.findAllGamesByStudio(studio);
        if (returnVal.size() > 0) {
            return returnVal;
        } else {
            return null;
        }
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {
        return gameRepo.save(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
        gameRepo.save(game);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        gameRepo.deleteById(id);
    }



}
