package com.company.FinalProjectSaraMorita.RepoTest;

import com.company.FinalProjectSaraMorita.models.Game;
import com.company.FinalProjectSaraMorita.repositories.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Before
    public void setUp() throws Exception
    {
        gameRepository.deleteAll();
    }

    @Test
    public void shouldUpdateGame()
    {
        // First create Game
        Game game = new Game();
        game.setTitle("Hello World");
        game.setEsrbRating("A");
        game.setDescription("Python Game #1");
        game.setPrice(new BigDecimal("1.99"));
        game.setStudio("Sony");
        game.setQuantity(15);

        game = gameRepository.save(game);

        game.setPrice(new BigDecimal("4.99"));
        game.setQuantity(1);

        gameRepository.save(game);

        Optional<Game> testGame = gameRepository.findById(game.getId());
        assertEquals(testGame.get(), game);
    }

    @Test
    public void shouldFindAllGames()
    {
        Game game = new Game();
        game.setTitle("Hello World");
        game.setEsrbRating("A");
        game.setDescription("Python Game #1");
        game.setPrice(new BigDecimal("1.99"));
        game.setStudio("Sony");
        game.setQuantity(15);
        game = gameRepository.save(game);

        Game game2 = new Game();
        game2.setTitle("Hello World 2");
        game2.setEsrbRating("B");
        game2.setDescription("Python Game #2");
        game2.setPrice(new BigDecimal("3.99"));
        game2.setStudio("Sony");
        game2.setQuantity(15);
        game2 = gameRepository.save(game2);

        Game game3 = new Game();
        game3.setTitle("Hello World 3");
        game3.setEsrbRating("A+");
        game3.setDescription("Python Game #3");
        game3.setPrice(new BigDecimal("5.99"));
        game3.setStudio("Sony");
        game3.setQuantity(5);
        game3 = gameRepository.save(game3);

        List<Game> allGames = gameRepository.findAll();
        assertEquals(allGames.size(),3);
    }



}
