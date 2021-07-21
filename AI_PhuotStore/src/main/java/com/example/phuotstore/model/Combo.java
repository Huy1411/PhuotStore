package com.example.phuotstore.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comboID;

    @NotNull(message = "Combo Name must not be null")
    private String comboName;

    @NotNull(message = "Combo Code must not be null")
    private String comboCode;

    @NotNull(message = "Combo Description must not be null")
    private String comboDesc;

    private int discount;

    @NotNull(message = "Quantity must not be null")
    private int qty;

    private long totalPrice;

    @Min(value = 1,message = "Please chose a status")
    private int status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "comboDetail",
        joinColumns = @JoinColumn(name = "comboID"),
        inverseJoinColumns = @JoinColumn(name = "productID"))
    private Set<Product> products = new HashSet<>();

    public Combo() {
    }

    public Combo(String comboName, String comboCode, String comboDesc, int discount, int qty, int status) {
        this.comboName = comboName;
        this.comboCode = comboCode;
        this.comboDesc = comboDesc;
        this.discount = discount;
        this.qty = qty;
        this.status = status;
    }

    public int getComboID() {
        return comboID;
    }

    public void setComboID(int comboID) {
        this.comboID = comboID;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboCode() {
        return comboCode;
    }

    public void setComboCode(String comboCode) {
        this.comboCode = comboCode;
    }

    public String getComboDesc() {
        return comboDesc;
    }

    public void setComboDesc(String comboDesc) {
        this.comboDesc = comboDesc;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
