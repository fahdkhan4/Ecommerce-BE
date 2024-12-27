package com.example.ecommerce.model;

public class Location {
    private int id;

    private String city;

    private String country;

    private String address;

    public Location() {
    }

    public Location(String city, String country, String address) {
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public Location(int id, String city, String country, String address) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
