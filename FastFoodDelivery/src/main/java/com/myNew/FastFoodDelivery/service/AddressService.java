package com.myNew.FastFoodDelivery.service;

import com.myNew.FastFoodDelivery.model.Address;
import com.myNew.FastFoodDelivery.model.Food;

import java.util.Set;

public interface AddressService {

    Address save(Address address);

    Address findByFullAddress(String fullAddress);

    Address findById (Long id);

    Address update(Address address,Long id);

    void delete (Long id);

    Set<Address> findAll() ;

    Address findByPaymentMethod(String paymentMethod );
}
