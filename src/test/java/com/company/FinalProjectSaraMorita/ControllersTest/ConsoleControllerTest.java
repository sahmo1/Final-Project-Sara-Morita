package com.company.FinalProjectSaraMorita.ControllersTest;

import com.company.FinalProjectSaraMorita.models.Console;
import com.company.FinalProjectSaraMorita.repositories.ConsoleRepository;
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
@WebMvcTest(ConsoleControllerTest.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Console console;
    private String consoleJson;
    private List<Console> allConsoles = new ArrayList<>();
    private String allConsolesJson;

    @Before
    public void setup() throws Exception
    {
        console = new Console();
        console.setId(1);
        console.setModel("Nintendo Switch");
        console.setManufacturer("Sony");
        console.setMemoryAmount("100");
        console.setProcessor("Windows");
        console.setPrice(new BigDecimal("99.50"));
        console.setQuantity(2);

        consoleJson = mapper.writeValueAsString(console);


        Console console2 = new Console();
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setMemoryAmount("10 GB");
        console2.setProcessor("Mac");
        console2.setPrice(new BigDecimal("123.50"));
        console2.setQuantity(10);

        allConsoles.add(console);
        allConsoles.add(console2);

        allConsolesJson = mapper.writeValueAsString(allConsoles);
    }

    @Test
    public void shouldCreateNewConsoleOnPostRequest() throws Exception
    {

        console =  new Console();
        console.setId(1);
        console.setModel("Nintendo Switch 2");
        console.setManufacturer("Sony");
        console.setMemoryAmount("300");
        console.setProcessor("Windows");
        console.setPrice(new BigDecimal("134.50"));
        console.setQuantity(8);

        Console inputConsole = new Console();
        inputConsole.setModel("X Box");
        inputConsole.setManufacturer("Microsoft");
        inputConsole.setMemoryAmount("100 GB");
        inputConsole.setProcessor("Windows");
        inputConsole.setPrice(new BigDecimal("599.99"));
        inputConsole.setQuantity(2);

        consoleJson = mapper.writeValueAsString(console);

        String inputJson = mapper.writeValueAsString(inputConsole);

        doReturn(console).when(repo).save(inputConsole);

        mockMvc.perform(
                        post("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(consoleJson));
    }

    @Test
    public void shouldReturnConsoleById() throws Exception
    {

        doReturn(Optional.of(console)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/consoles/1"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().json(consoleJson))
                );


    }

    @Test
    public void shouldReturnConsoleByManufacturer() throws Exception
    {
        Console console2 = new Console();
        console2.setModel("Nintendo Switch");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("100");
        console2.setProcessor("Windows");
        console2.setPrice(new BigDecimal("229.99"));
        console2.setQuantity(1);

        List<Console> consolesByManufacturers = new ArrayList<>();
        consolesByManufacturers.add(console2);

        doReturn(console2).when(repo).save(console2);

        String inputJson = mapper.writeValueAsString(console2);

        consoleJson = mapper.writeValueAsString(consolesByManufacturers);

        doReturn(consolesByManufacturers).when(repo).findAllConsolesByManufacturer("Sony");

        ResultActions result = mockMvc.perform(
                        get("/consoles/manufacturer/Sony"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().json(consoleJson))
                );
    }

    @Test
    public void shouldBStatusOkForNonExistentConsoleId() throws Exception
    {
        mockMvc.perform(
                        get("/consoles/9999"))
                .andExpect(status().isOk()
                );

    }

    @Test
    public void shouldReturnAllConsoles() throws Exception {
        doReturn(allConsoles).when(repo).findAll();

        mockMvc.perform(
                        get("/consoles"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(allConsolesJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception
    {
        mockMvc.perform(
                        put("/consoles")
                                .content(consoleJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {

        Console console2 = new Console();
        console2.setId(2);
        console2.setModel("Nintendo Switch");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("100");
        console2.setProcessor("Windows");
        console2.setPrice(new BigDecimal("229.99"));
        console2.setQuantity(5);

        doReturn(console2).when(repo).save(console2);
        mockMvc.perform(delete("/consoles/2")).andExpect(status().isNoContent());
    }

    


}
