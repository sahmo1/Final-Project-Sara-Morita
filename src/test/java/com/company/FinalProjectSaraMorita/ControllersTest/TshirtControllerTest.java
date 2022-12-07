package com.company.FinalProjectSaraMorita.ControllersTest;

import com.company.FinalProjectSaraMorita.controllers.TshirtControllers;
import com.company.FinalProjectSaraMorita.models.Tshirt;
import com.company.FinalProjectSaraMorita.repositories.TshirtRepository;
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
@WebMvcTest(TshirtControllers.class)
public class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TshirtRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Tshirt tshirt;
    private String tshirtJson;
    private List<Tshirt> allTshirts = new ArrayList<>();
    private String allTshirtsJson;


    @Before
    public void setup() throws Exception
    {

        tshirt = new Tshirt();
        tshirt.setTShirtID(1);
        tshirt.setSize("Small");
        tshirt.setColor("White");
        tshirt.setDescription("Simple Tshirt");
        tshirt.setPrice(new BigDecimal(5.99));
        tshirt.setQuantity(10);

        tshirtJson = mapper.writeValueAsString(tshirt);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setSize("XXL");
        tshirt2.setColor("Black");
        tshirt2.setDescription("Simple Tshirt Big Size");
        tshirt2.setPrice(new BigDecimal(6.99));
        tshirt2.setQuantity(10);

        allTshirts.add(tshirt);
        allTshirts.add(tshirt2);

        allTshirtsJson = mapper.writeValueAsString(allTshirts);
    }

    @Test
    public void shouldReturnAllTshirts() throws Exception {
        doReturn(allTshirts).when(repo).findAll();

        mockMvc.perform(
                        get("/tshirts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(allTshirtsJson)
                );
    }

    @Test
    public void shouldCreateNewTshirtOnPostRequest() throws Exception
    {
        tshirt = new Tshirt();
        tshirt.setTShirtID(1);
        tshirt.setSize("Large");
        tshirt.setColor("Pink");
        tshirt.setDescription("Pink Shirt");
        tshirt.setPrice(new BigDecimal(3.99));
        tshirt.setQuantity(20);


        Tshirt inputTshirt = new Tshirt();
        inputTshirt.setTShirtID(1);
        inputTshirt.setSize("XS");
        inputTshirt.setColor("Grey");
        inputTshirt.setDescription("Grey tshirt");
        inputTshirt.setPrice(new BigDecimal(5.99));
        inputTshirt.setQuantity(10);

        tshirtJson = mapper.writeValueAsString(tshirt);

        String inputJson = mapper.writeValueAsString(inputTshirt);

        doReturn(tshirt).when(repo).save(inputTshirt);

        mockMvc.perform(
                        post("/tshirts")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(tshirtJson));
    }

    @Test
    public void shouldReturnTshirtById() throws Exception
    {

        doReturn(Optional.of(tshirt)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/tshirts/1"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().json(tshirtJson))
                );
    }

    @Test
    public void shouldBStatusOkForNonExistentTshirtId() throws Exception
    {
        doReturn(Optional.empty()).when(repo).findById(3);

        mockMvc.perform(
                        get("/tshirts/2222"))
                .andExpect(status().is4xxClientError()
                );

    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception
    {
        mockMvc.perform(
                        put("/tshirts")
                                .content(tshirtJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }





}
