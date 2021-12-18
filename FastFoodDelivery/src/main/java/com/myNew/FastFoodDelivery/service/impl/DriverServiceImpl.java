package com.myNew.FastFoodDelivery.service.impl;

import com.myNew.FastFoodDelivery.exception.DublicateResourceException;
import com.myNew.FastFoodDelivery.exception.ThisDataIsNotFoundException;
import com.myNew.FastFoodDelivery.model.Driver;
import com.myNew.FastFoodDelivery.model.Restaurant;
import com.myNew.FastFoodDelivery.repository.DriverRepository;
import com.myNew.FastFoodDelivery.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Set<Driver> findAll() {
        return new HashSet<>(driverRepository.findAll());
    }

    @Override
    public Driver findByEfficiencyLevel(double efficiencyLevel) {
        return driverRepository.findByEfficiencyLevel(efficiencyLevel)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Driver witn efficiencyLevel %s doesn`t exists!", efficiencyLevel)));
    }

    @Override
    public Driver save(Driver driver) {
        try {
            return driverRepository.save(driver);
        } catch (DataIntegrityViolationException exception) {
            throw new DublicateResourceException
                    (String.format("Driver with name %d already exists!", driver.getDriverName()));
        }
    }

    @Override
    public Driver findByDriverName(String driverName) {
        return driverRepository.findByDriverName(driverName)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Driver witn %s doesn`t exists!", driverName)));
    }

    @Override
    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Driver with id %d doesn`t exists!", id)));
    }

    @Override
    public Driver update(Driver driver, Long id) {
        Driver foundDriver = findById(id);
        Driver updatedDriver = Driver.builder()
                .id(foundDriver.getId())
                .driverName(driver.getDriverName())
                .build();

        return save(updatedDriver);
    }

    @Override
    public void delete(Long id) {
    driverRepository.deleteById(id);
    }


}
