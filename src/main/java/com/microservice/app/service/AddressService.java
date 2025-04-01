package com.microservice.app.service;

import com.microservice.app.addressRequest.AddressRequest;
import com.microservice.app.addressRespone.AddressResponse;
import com.microservice.app.entity.Address;
import com.microservice.app.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public AddressResponse createAddress(AddressRequest addressRequest) {

        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreetName());

        addressRepository.save(address);
        log.info("Successfully inserted into address table");
        return new AddressResponse(address);
    }

    public AddressResponse getById(Long id) {
        log.info("Inside getById" + id);
       Address address = addressRepository.findById(id).get();
        return new AddressResponse(address);
    }
}
