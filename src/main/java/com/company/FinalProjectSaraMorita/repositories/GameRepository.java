package com.company.FinalProjectSaraMorita.repositories;

import com.company.FinalProjectSaraMorita.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    public List<Game> findAllGamesByStudio(String studio);
    public List<Game> findAllGamesByTitle(String title);
    public List<Game> findAllGamesByEsrbRating(String esrbRating);


}
