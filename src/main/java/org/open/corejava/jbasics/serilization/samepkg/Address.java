package org.open.corejava.jbasics.serilization.samepkg;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    String street, city, state, country;

    public Address(String street, String city, String state, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address [Street: " + street + ", City: " + city + ", State: " + state + ", Country: " + country + "]";
    }
}
