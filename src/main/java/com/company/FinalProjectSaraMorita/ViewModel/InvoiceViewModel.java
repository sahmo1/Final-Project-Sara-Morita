package com.company.FinalProjectSaraMorita.ViewModel;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {

    private int id;

    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal subtotal;
    private BigDecimal total;


    @NotEmpty(message = "Enter a name.")
    private String name;

    @NotEmpty(message = "Enter a street.")
    private String street;

    @NotEmpty(message = "Enter a city.")
    private String city;

    @NotEmpty(message = "Enter a zipcode.")
    @Min(value = 5, message = "Zipcode must be 5 digits")
    @Max(value = 5, message = "Zipcode must be 5 digits")
    private String zipcode;

    @NotEmpty(message = "Enter a state.")
    @Length(max = 2,min = 2, message = "Enter state's abbreviation.")
    private String state;

    @NotEmpty(message = "Specify item type.")
    @Column(name = "item_type")
    private String itemType;

    private BigDecimal unitPrice;

    @NotEmpty(message = "Specific item quantity.")
    @Min(value = 1, message = "Quantity must be minimum 1.")
    private int quantity;

    @NotEmpty(message = "Specify item ID.")
    @Column(name = "item_id")
    private int itemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return id == that.id && itemId == that.itemId && quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode) && Objects.equals(itemType, that.itemType) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(subtotal, that.subtotal) && Objects.equals(tax, that.tax) && Objects.equals(processingFee, that.processingFee) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", item_type='" + itemType + '\'' +
                ", item_id=" + itemId +
                ", unit_price=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processing_fee=" + processingFee +
                ", total=" + total +
                '}';
    }






}
