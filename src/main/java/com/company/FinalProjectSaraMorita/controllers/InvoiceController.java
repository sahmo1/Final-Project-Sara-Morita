package com.company.FinalProjectSaraMorita.controllers;

import com.company.FinalProjectSaraMorita.ViewModel.InvoiceViewModel;
import com.company.FinalProjectSaraMorita.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoiceViewModel(@RequestBody InvoiceViewModel invoiceViewModel){
        return serviceLayer.saveInvoice(invoiceViewModel);
    }
}
