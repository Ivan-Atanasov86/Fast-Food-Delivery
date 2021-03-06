package com.myNew.FastFoodDelivery.converter;

import com.myNew.FastFoodDelivery.dto.RestaurantDto;
import com.myNew.FastFoodDelivery.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantConverter {

    public RestaurantDto toRestaurantDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .restaurantName(restaurant.getRestaurantName().toString())
                .build();
    }

    public Restaurant toRestaurant(RestaurantDto restaurantDto) {
    return Restaurant.builder()
            .restaurantName(restaurantDto.getRestaurantName())
            .build();

    }

}
