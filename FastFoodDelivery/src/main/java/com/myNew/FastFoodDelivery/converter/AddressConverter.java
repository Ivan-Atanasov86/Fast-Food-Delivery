package com.myNew.FastFoodDelivery.converter;

import com.myNew.FastFoodDelivery.dto.AddressDto;
import com.myNew.FastFoodDelivery.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public AddressDto toAddressDto(Address address) {
        return AddressDto.builder()
                .paymentMethod(address.getPaymentMethod())
                .build();
    }

    public Address toAddress(AddressDto addressDto) {
        return Address.builder()
                .paymentMethod(addressDto.getPaymentMethod())
                .build();
    }
}