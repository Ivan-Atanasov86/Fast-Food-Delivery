package com.myNew.FastFoodDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DriverDto {

    private Long id;

    private double efficiencyLevel;
}
