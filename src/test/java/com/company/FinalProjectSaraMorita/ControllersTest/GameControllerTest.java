package com.company.FinalProjectSaraMorita.ControllersTest;

import com.company.FinalProjectSaraMorita.controllers.GameControllers;
import com.company.FinalProjectSaraMorita.models.Game;
import com.company.FinalProjectSaraMorita.repositories.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameControllers.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Game game;
    private String gameJson;
    private List<Game> allGames = new ArrayList<>();
    private String allGamesJson;

    @Before
    public void setup() throws Exception
    {
        game = new Game();
        game.setId(1);
        game.setTitle("Hello World");
        game.setEsrbRating("A");
        game.setDescription("Python Game");
        game.setPrice(new BigDecimal("345.90"));
        game.setStudio("Sony");
        game.setQuantity(100);

        gameJson = mapper.writeValueAsString(game);

        Game game2 = new Game();
        game2.setTitle("Hello World 2");
        game2.setEsrbRating("B");
        game2.setDescription("Python Game #2");
        game2.setPrice(new BigDecimal("355.99"));
        game2.setStudio("Sony");
        game2.setQuantity(50);

        allGames.add(game);
        allGames.add(game2);


        allGamesJson = mapper.writeValueAsString(allGames);
    }

    @Test
    public void shouldCreateNewGameOnPostRequest() throws Exception
    {
        game = new Game();
        game.setId(1);
        game.setTitle("Hello World");
        game.setEsrbRating("A");
        game.setDescription("Python Game");
        game.setPrice(new BigDecimal("345.90"));
        game.setStudio("Sony");
        game.setQuantity(100);

        gameJson = mapper.writeValueAsString(game);

        Game game2 = new Game();
        game2.setTitle("Hello World 2");
        game2.setEsrbRating("B");
        game2.setDescription("Python Game #2");
        game2.setPrice(new BigDecimal("355.99"));
        game2.setStudio("Sony");
        game2.setQuantity(50);

        String inputJson = mapper.writeValueAsString(game2);

        doReturn(game).when(repo).save(game2);

        mockMvc.perform(
                        post("/games")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(gameJson));
    }

    @Test
    public void shouldReturnGameByTitle() throws Exception
    {
        Game game2 = new Game();
        game2.setTitle("Hello World 2");
        game2.setEsrbRating("B");
        game2.setDescription("Python Game #2");
        game2.setPrice(new BigDecimal("355.99"));
        game2.setStudio("Sony");
        game2.setQuantity(50);

        List<Game> allGamesByTitle = new ArrayList<>();
        allGamesByTitle.add(game2);

        doReturn(game2).when(repo).save(game2);

        String inputJson = mapper.writeValueAsString(game2);

        allGamesJson = mapper.writeValueAsString(allGamesByTitle);

        doReturn(allGamesByTitle).when(repo).findAllGamesByTitle("Hello World 2");

        ResultActions result = mockMvc.perform(
                        get("/games/title/Hello World 2"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().json(allGamesJson))
                );
    }

    @Test
    public void shouldReturnGameById() throws Exception
    {
        doReturn(Optional.of(game)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/games/1"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().json(gameJson))
                );
    }


    @Test
    public void shouldReturnAllGames() throws Exception {
        doReturn(allGames).when(repo).findAll();

        mockMvc.perform(
                        get("/games"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(allGamesJson)
                );
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        game = new Game();
        game.setId(1);
        game.setTitle("Hello World");
        game.setEsrbRating("A");
        game.setDescription("Python Game");
        game.setPrice(new BigDecimal("345.90"));
        game.setStudio("Sony");
        game.setQuantity(100);

        doReturn(game).when(repo).save(game);

        mockMvc.perform(delete("/games/1")).andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception
    {
        mockMvc.perform(
                        put("/games")
                                .content(gameJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }










}
