package com.myNew.FastFoodDelivery.converter;

import com.myNew.FastFoodDelivery.dto.RestaurantDto;
import com.myNew.FastFoodDelivery.model.Restaurant;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class RestaurantConverter {

    public RestaurantDto toRestaurantDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .RestaurantName(restaurant.getRestaurantName())
                .build();
    }

    public Restaurant toRestaurant(RestaurantDto restaurantDto) {
    return Restaurant.builder()
            .restaurantName(restaurantDto.getRestaurantName())
            .build();

    }

}
