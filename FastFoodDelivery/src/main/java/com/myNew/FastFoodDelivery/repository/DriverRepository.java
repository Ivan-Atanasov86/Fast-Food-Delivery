package com.myNew.FastFoodDelivery.repository;

import com.myNew.FastFoodDelivery.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {


     Optional<Driver> findByDriverName(String driverName);
     Optional<Driver> findByEfficiencyLevel(double efficiencylevel);
}
