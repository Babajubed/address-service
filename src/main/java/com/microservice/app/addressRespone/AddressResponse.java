package com.microservice.app.addressRespone;

import com.microservice.app.entity.Address;

public class AddressResponse {

    private String streetName;

    private String city;

    private long id;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.city = address.getCity();
        this.streetName = address.getStreet();
    }


    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

