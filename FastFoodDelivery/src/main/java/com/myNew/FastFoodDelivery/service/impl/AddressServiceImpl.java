package com.myNew.FastFoodDelivery.service.impl;

import com.myNew.FastFoodDelivery.exception.DublicateResourceException;
import com.myNew.FastFoodDelivery.exception.ThisDataIsNotFoundException;
import com.myNew.FastFoodDelivery.model.Address;
import com.myNew.FastFoodDelivery.model.Food;
import com.myNew.FastFoodDelivery.repository.AddressRepository;
import com.myNew.FastFoodDelivery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findByFullAddress(String fullAddress) {
        return addressRepository.findByFullAddress(fullAddress)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Address witn %s doesn`t exists!", fullAddress)));
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Address with id %d doesn`t exists!", id)));
    }

    @Override
    public Address update(Address address, Long id) {
        Address foundAddress = findById(id);
        Address updatedAddress = Address.builder()
                .id(foundAddress.getId())
                .fullAddress(address.getFullAddress())
                .build();

        return save(updatedAddress);
    }

    @Override
    public void delete(Long id) {
    addressRepository.deleteById(id);
    }

    @Override
    public Set<Address> findAll() {
        return new HashSet<>(addressRepository.findAll());
    }

    @Override
    public Address findByPaymentMethod(String paymentMethod) {
        return addressRepository.findByPaymentMethod(paymentMethod)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Address with payment metod %d doesn`t exists!", paymentMethod)));
    }

    @Override
    public Address save(Address address) {
        try {
            return addressRepository.save(address);
        } catch (DataIntegrityViolationException exception) {
            throw new DublicateResourceException
                    (String.format("Address with name %d already exists!", address.getFullAddress()));
        }
}
    }