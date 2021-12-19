package com.myNew.FastFoodDelivery.converter;

import com.myNew.FastFoodDelivery.dto.DriverDto;
import com.myNew.FastFoodDelivery.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverConverter {

    public DriverDto toDriverDto(Driver driver) {
        return DriverDto.builder()
                .efficiencyLevel(driver.getEfficiencyLevel())
                .build();
    }

    public Driver toDriver(DriverDto driverDto) {
        return Driver.builder()
                .efficiencyLevel(driverDto.getEfficiencyLevel())
                .build();

    }
}
