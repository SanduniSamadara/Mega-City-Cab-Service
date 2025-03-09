package com.megacity.model;

public class Booking {
    private String bookingNumber;
    private String customerName;
    private String driver;
    private String vehicle;
    private String destinationFrom;
    private String destinationTo;
    private double distance;

    public Booking(String bookingNumber, String customerName, String vehicle, String driver, String destinationFrom, String destinationTo, double distance) {
        this.bookingNumber = bookingNumber;
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.driver = driver;
        this.destinationFrom = destinationFrom;
        this.destinationTo = destinationTo;
        this.distance = distance;    }

    public Booking() {
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
    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDestinationFrom() {
        return destinationFrom;
    }

    public void setDestinationFrom(String destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    public String getDestinationTo() {
        return destinationTo;
    }

    public void setDestinationTo(String destinationTo) {
        this.destinationTo = destinationTo;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Booking [ bookingNumber=" + bookingNumber + ", customerName=" + customerName +
                ", vehicle=" + vehicle + ", driver=" + driver + ", destinationFrom=" + destinationFrom + ", destinationTo=" + destinationTo + ", distance=" + distance +"]";
    }
}
