package com.myNew.FastFoodDelivery.controller;


import com.myNew.FastFoodDelivery.converter.AddressConverter;
import com.myNew.FastFoodDelivery.dto.AddressDto;
import com.myNew.FastFoodDelivery.model.Address;
import com.myNew.FastFoodDelivery.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressConverter addressConverter;

    public AddressController(AddressService addressService, AddressConverter addressConverter) {
        this.addressService = addressService;
        this.addressConverter = addressConverter;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDto> update(@RequestBody @Valid AddressDto addressDto,
                                             @PathVariable Long id) {
        Address address = addressConverter.toAddress(addressDto);
        Address updateAddress = addressService.update(address, id);
        return ResponseEntity.ok(addressConverter.toAddressDto(updateAddress));
    }

    @PostMapping
    public ResponseEntity<AddressDto> save(@RequestBody @Valid AddressDto addressDto) {
        Address address = addressConverter.toAddress(addressDto);
        Address savedAddress = addressService.save(address);
        return ResponseEntity.ok(addressConverter.toAddressDto(savedAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/paymentMethod/{paymentMethod}")
    public ResponseEntity<AddressDto> findByPaymentMethod(@PathVariable String paymentMethod) {
        return ResponseEntity.ok
                (addressConverter.toAddressDto(addressService.findByPaymentMethod(paymentMethod)));
    }
}
