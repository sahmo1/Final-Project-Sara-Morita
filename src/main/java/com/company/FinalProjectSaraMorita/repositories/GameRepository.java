package com.company.FinalProjectSaraMorita.repositories;

import com.company.FinalProjectSaraMorita.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findAllGamesByStudio(String studio);
    List<Game> findAllGamesByTitle(String title);
    List<Game> findAllGamesByEsrbRating(String esrbRating);


}
