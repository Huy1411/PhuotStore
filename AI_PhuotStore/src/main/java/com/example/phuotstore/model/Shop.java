package com.example.phuotstore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopID;

    @NotNull(message = "Shop Name must not be null")
    private String shopName;

    @NotNull(message = "Phone must not be null")
    private String phone;

    @NotNull(message = "Address must not be null")
    private String address;

    @Min(value = 1,message = "Please chose a status")
    private int status;

    private String country;

    private String region;

    public Shop() {
    }

    public Shop(int shopID, String shopName, String phone, String address, int status, String country, String region) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.country = country;
        this.region = region;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
