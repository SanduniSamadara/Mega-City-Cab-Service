package com.megacity.model;

public class Driver {
    private int id;
    private String name;
    private String licenseNumber;
    private String address;
    private String contactNumber;

    // Constructor
    public Driver(int id, String name, String licenseNumber, String address, String contactNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.address = address;
        this.contactNumber = contactNumber;
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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Driver [id=" + id + ", name=" + name + ", licenseNumber=" + licenseNumber + ", address=" + address + ", contactNumber=" + contactNumber + "]";
    }
}
