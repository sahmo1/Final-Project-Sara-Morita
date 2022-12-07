package com.company.FinalProjectSaraMorita.service;

import com.company.FinalProjectSaraMorita.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Component
public class ServiceLayer {

    private InvoiceRepository invoiceRepository;
    private SalesTaxRepository salesTaxRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private TshirtRepository tshirtRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;

    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository, GameRepository gameRepository,
                        TshirtRepository tshirtRepository, ProcessingFeeRepository processingFeeRepository, SalesTaxRepository salesTaxRepository,
                        InvoiceRepository invoiceRepository){

        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.tshirtRepository = tshirtRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.salesTaxRepository = salesTaxRepository;
        this.invoiceRepository = invoiceRepository;

    }



}
