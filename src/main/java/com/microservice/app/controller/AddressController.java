package com.microservice.app.controller;


import com.microservice.app.addressRequest.AddressRequest;
import com.microservice.app.addressRespone.AddressResponse;
import com.microservice.app.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody AddressRequest request) {
        return addressService.createAddress(request);
    }

    @GetMapping("/getId/{id}")
    public AddressResponse getId(@PathVariable Long id) {
        return addressService.getById(id);
    }
}
