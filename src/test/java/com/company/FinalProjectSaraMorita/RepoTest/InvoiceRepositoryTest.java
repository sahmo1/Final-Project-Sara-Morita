package com.company.FinalProjectSaraMorita.RepoTest;

import com.company.FinalProjectSaraMorita.ViewModel.InvoiceViewModel;
import com.company.FinalProjectSaraMorita.models.Game;
import com.company.FinalProjectSaraMorita.repositories.*;
import com.company.FinalProjectSaraMorita.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @Autowired
    TshirtRepository tshirtRepository;

    @Autowired
    ProcessingFeeRepository processingFeeRepository;

    @Autowired
    SalesTaxRepository salesTaxRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        gameRepository.deleteAll();
        consoleRepository.deleteAll();
        tshirtRepository.deleteAll();
        invoiceRepository.deleteAll();
    }


}
