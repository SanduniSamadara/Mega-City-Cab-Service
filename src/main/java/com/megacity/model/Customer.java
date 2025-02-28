package com.megacity.model;

public class Customer {
    private int id;
    private String registrationNumber;
    private String name;
    private String address;
    private String nic;

    // Constructor
    public Customer(int id, String registrationNumber, String name, String address, String nic) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.nic = nic;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", registrationNumber=" + registrationNumber + ", name=" + name +
                ", address=" + address + ", nic=" + nic + "]";
    }
}