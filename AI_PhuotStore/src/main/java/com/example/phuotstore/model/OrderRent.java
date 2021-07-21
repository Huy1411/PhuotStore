package com.example.phuotstore.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderRentID;

    @NotNull(message = "Order Rent Name must not be null")
    private String orderRentName;

    private String note;

    @CreationTimestamp
    private Date createAt;

    @CreationTimestamp
    private Date updateAt;

    @CreationTimestamp
    private Date bookingDate;

    @CreationTimestamp
    private Date rentalStart;

    @CreationTimestamp
    private Date rentalEnd;

    @Min(value = 1,message = "Please chose a status")
    private int status;

    private int totalQuantity;

    private double totalPrice;

    private double rental;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderRentProduct",
        joinColumns = @JoinColumn(name = "orderRentID"),
        inverseJoinColumns = @JoinColumn(name = "productID"))
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderRentCombo",
        joinColumns = @JoinColumn(name = "orderRentID"),
        inverseJoinColumns = @JoinColumn(name = "comboID"))
    private Set<Combo> combos = new HashSet<>();

    public OrderRent() {
    }

    public OrderRent(@NotNull String orderRentName, String note, Date createAt, Date updateAt, Date bookingDate, Date rentalStart, Date rentalEnd, int status, int totalQuantity, double totalPrice) {
        this.orderRentName = orderRentName;
        this.note = note;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.bookingDate = bookingDate;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public int getOrderRentID() {
        return orderRentID;
    }

    public void setOrderRentID(int orderRentID) {
        this.orderRentID = orderRentID;
    }

    public String getOrderRentName() {
        return orderRentName;
    }

    public void setOrderRentName(String orderRentName) {
        this.orderRentName = orderRentName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public Date getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getRental() {
        return rental;
    }

    public void setRental(double rental) {
        this.rental = rental;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Combo> getCombos() {
        return combos;
    }

    public void setCombos(Set<Combo> combos) {
        this.combos = combos;
    }
}
