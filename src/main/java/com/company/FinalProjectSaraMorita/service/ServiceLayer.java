package com.company.FinalProjectSaraMorita.service;
import com.company.FinalProjectSaraMorita.models.*;
import com.company.FinalProjectSaraMorita.ViewModel.*;
import com.company.FinalProjectSaraMorita.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

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

    public boolean isZipcode(String zipcode)
    {
        //if zipcode is null
        if (zipcode == null ){
            throw new IllegalArgumentException("Invalid zipcode");
        }
        if (zipcode != null){

            //check for length
            boolean isValidLength = zipcode.length() == 5;
            //check if the zipcode is an int
            boolean isValidInt = zipcode.matches("[0-9]+");

            if (isValidLength == true && isValidInt == true){
                return true;
            }
        }
        return false;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel)
    {

        BigDecimal finalPrice = new BigDecimal(0);
        BigDecimal price;
        int inventory;
        int quantity = invoiceViewModel.getQuantity();
        String state = invoiceViewModel.getState();
        String zipcode = invoiceViewModel.getZipcode();

        //check for valid zipcode 
        if (isZipcode(zipcode) == false){
            throw new IllegalArgumentException("Invalid zipcode");
        }

        //check for valid quantity
        if (quantity < 1){
            throw new IllegalArgumentException("Invalid quantity");
        }

        //PART 1: check for type of console
        if (invoiceViewModel.getItemType().equals("Console")){
            Optional<Console> console = consoleRepository.findById(invoiceViewModel.getId());

            if (console.isPresent()){
                price = console.get().getPrice();
                inventory = console.get().getQuantity();

                int newInventory = inventory - quantity;

                if (inventory < quantity){
                    throw new IllegalArgumentException("Not enough inventory");
                }

                Console c = new Console();

                //set id, model, manufacturer, memory_amount, processor, price, quantity
                c.setId(console.get().getId());
                c.setModel(console.get().getModel());
                c.setManufacturer(console.get().getManufacturer());
                c.setMemoryAmount(console.get().getMemoryAmount());
                c.setProcessor(console.get().getProcessor());
                c.setPrice(console.get().getPrice());
                c.setQuantity(newInventory);

                //save console
                c = consoleRepository.save(c);
            }

            if (!console.isPresent()){
                throw new IllegalArgumentException("Invalid console");
            }
        }
    

        //PART 2: Check for Game

        if (invoiceViewModel.getItemType().equals("Game"))
        {
            Optional<Game> game = gameRepository.findById(invoiceViewModel.getId());

            if (!game.isPresent()){
                throw new IllegalArgumentException("Invalid game");
            }

            if (game.isPresent()){
                price = game.get().getPrice();
                inventory = game.get().getQuantity();

                int newInventory = inventory - quantity;

                if (inventory < quantity){
                    throw new IllegalArgumentException("Not enough inventory");
                }

                Game g = new Game();

                //set id, title, esrb_rating, description, price, studio, quantity
                g.setId(game.get().getId());
                g.setTitle(game.get().getTitle());
                g.setEsrbRating(game.get().getEsrbRating());
                g.setDescription(game.get().getDescription());
                g.setPrice(game.get().getPrice());
                g.setStudio(game.get().getStudio());
                g.setQuantity(newInventory);

                //save game
                g = gameRepository.save(g);
            }
        }

        //PART 3: Check for Tshirt
        if (invoiceViewModel.getItemType().equals("T-shirt")){
            Optional<Tshirt> tshirt = tshirtRepository.findById(invoiceViewModel.getId());

            if (!tshirt.isPresent()){
                throw new IllegalArgumentException("Invalid tshirt");
            }

            if (tshirt.isPresent()){
                price = tshirt.get().getPrice();
                inventory = tshirt.get().getQuantity();

                int newInventory = inventory - quantity;

                if (inventory < quantity){
                    throw new IllegalArgumentException("Not enough inventory");
                }

                Tshirt t = new Tshirt();

                //set id, size, color, description, price, quantity
                t.setTShirtID(tshirt.get().getTshirtID());
                t.setSize(tshirt.get().getSize());
                t.setColor(tshirt.get().getColor());
                t.setDescription(tshirt.get().getDescription());
                t.setPrice(tshirt.get().getPrice());
                t.setQuantity(newInventory);

                //save tshirt
                t = tshirtRepository.save(t);
            }
        }

        //if the above 3 conditions are not met, throw an exception
        throw new IllegalArgumentException("Invalid item type");

        //PART 4: Setup processing fee
        ProcessingFee processingFeeItem = processingFeeRepository.findFeeByProductType(invoiceViewModel.getItemType());

        BigDecimal processingFeeAmount = processingFeeItem.getProcessingFee();
        BigDecimal additionalFee = BigDecimal.valueOf(15.49);
        BigDecimal totalProcessingFee = (quantity > 10) ? processingFeeAmount.add(additionalFee) : processingFeeAmount;


        //PART 5: Setup sales tax
        SalesTax salesTax = salesTaxRepository.findRateByState(state);
        BigDecimal stateTax = salesTax.getRate();
        BigDecimal totalStateTax = stateTax.multiply(price).multiply(BigDecimal.valueOf(quantity));

        //PART 6: Setup Final Price 
        finalPrice = price.multiply(BigDecimal.valueOf(quantity)).add(totalProcessingFee).add(totalStateTax);

        //round the finalPrice to 2 decimal places
        finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

        //PART 7: Setup Invoice
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setId(invoiceViewModel.getId());
        invoice.setUnitPrice(price);
        invoice.setQuantity(quantity);
        invoice.setSubtotal(price.multiply(BigDecimal.valueOf(quantity)));
        invoice.setTax(totalStateTax);
        invoice.setProcessing_fee(totalProcessingFee);
        invoice.setTotal(finalPrice);

        //save invoice
        invoice = invoiceRepository.save(invoice);

        //PART 8: Setup InvoiceViewModel
        invoiceViewModel.setId(invoice.getId());
        invoiceViewModel.setUnitPrice(price);
        invoiceViewModel.setSubtotal(price.multiply(BigDecimal.valueOf(quantity)));
        invoiceViewModel.setTax(totalStateTax);
        invoiceViewModel.setProcessingFee(totalProcessingFee);
        invoiceViewModel.setTotal(finalPrice);

        return invoiceViewModel;

    }
}
