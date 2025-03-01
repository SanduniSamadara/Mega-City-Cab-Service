package com.megacity.model;

public class Booking {
    private String bookingNumber;
    private String customerName;
    private String address;
    private String telephone;
    private String destination;

    public Booking() { }

    public Booking(String bookingNumber, String customerName, String address, String telephone, String destination) {
        this.bookingNumber = bookingNumber;
        this.customerName = customerName;
        this.address = address;
        this.telephone = telephone;
        this.destination = destination;
    }

    // Getters and Setters
    public String getBookingNumber() {
        return bookingNumber;
    }
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Booking [bookingNumber=" + bookingNumber + ", customerName=" + customerName +
                ", address=" + address + ", telephone=" + telephone + ", destination=" + destination + "]";
    }
}
