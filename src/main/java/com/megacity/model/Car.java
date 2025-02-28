package com.megacity.model;

public class Car {
    private int id;
    private String name;
    private String model;
    private String plateNumber;
    private int year;
    private double price;

    // Constructor
    public Car(int id, String name, String plateNumber, int year, double price, String model) {
        this.id = id;
        this.name = name;
        this.plateNumber = plateNumber;
        this.year = year;
        this.price = price;
        this.model = model;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", name=" + name + ", model=" + model + ", plateNumber=" + plateNumber + ", year=" + year + ", price=" + price + "]";
    }

}
