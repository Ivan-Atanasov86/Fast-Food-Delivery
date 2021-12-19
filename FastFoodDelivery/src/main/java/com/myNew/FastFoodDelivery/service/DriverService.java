package com.myNew.FastFoodDelivery.service;

import com.myNew.FastFoodDelivery.model.Driver;

import java.util.Set;

public interface DriverService {

    Driver save(Driver driver);

    Driver findByDriverName(String driverName);

    Driver findById (Long id);

    Driver update(Driver driver,Long id);

    void delete (Long id);

    Set<Driver> findAll() ;

    Driver findByEfficiencyLevel(double efficiencyLevel);
}
