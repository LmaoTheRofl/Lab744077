package org.example.organization;

import com.google.gson.annotations.Expose;

public class Address {
    @Expose(serialize = true)
    private String street; //Длина строки не должна быть больше 83, Поле может быть null
    @Expose(serialize = true)
    private Location town; //Поле не может быть null

    public Address(String street, Location town) {
        this.street = street;
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public Location getTown() {
        return town;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", town=" + town +
                '}';
    }
}
