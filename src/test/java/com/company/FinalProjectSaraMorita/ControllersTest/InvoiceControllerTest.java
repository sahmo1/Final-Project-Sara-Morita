package com.company.FinalProjectSaraMorita.ControllersTest;

import com.company.FinalProjectSaraMorita.ViewModel.InvoiceViewModel;
import com.company.FinalProjectSaraMorita.controllers.InvoiceController;
import com.company.FinalProjectSaraMorita.models.Game;
import com.company.FinalProjectSaraMorita.models.Invoice;
import com.company.FinalProjectSaraMorita.service.ServiceLayer;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Invoice invoice;
    private String invoiceJson;


    @Before
    public void setup() throws Exception {
        Game game = new Game();
        game.setId(1);
        game.setTitle("Hello World");
        game.setEsrbRating("A");
        game.setDescription("Python game");
        game.setPrice(new BigDecimal("4.99"));
        game.setStudio("Sony");
        game.setQuantity(25);
    }

    @Test
    public void shouldCreateNewInvoiceOnPostRequest() throws Exception
    {
        InvoiceViewModel inputInvoice = new InvoiceViewModel();
        inputInvoice.setName("Customer 1");
        inputInvoice.setStreet("123 Hello World");
        inputInvoice.setCity("New York");
        //Tax: ('WY', .04);
        inputInvoice.setState("NY");
        inputInvoice.setZipcode("12345");
        inputInvoice.setItemId(1);
        inputInvoice.setItemType("Game");
        inputInvoice.setQuantity(10);

        String inputJson = mapper.writeValueAsString(inputInvoice);

        InvoiceViewModel returnInvoice = new InvoiceViewModel();
        returnInvoice.setName("Customer 1");
        returnInvoice.setStreet("123 Hello World");
        returnInvoice.setCity("New York");

        returnInvoice.setState("NY");
        returnInvoice.setZipcode("12345");
        returnInvoice.setItemId(1);
        returnInvoice.setItemType("Game");
        returnInvoice.setQuantity(10);

        returnInvoice.setUnitPrice(new BigDecimal("3.00"));
        returnInvoice.setProcessingFee(new BigDecimal("2.00"));
        returnInvoice.setSubtotal(new BigDecimal("30.00"));
        returnInvoice.setTax(new BigDecimal("1.00"));
        returnInvoice.setTotal(new BigDecimal("38.00"));

        String returnJson = mapper.writeValueAsString(returnInvoice);
        doReturn(returnInvoice).when(repo).saveInvoice(inputInvoice);

        mockMvc.perform(
                        post("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(returnJson));
    }


}
