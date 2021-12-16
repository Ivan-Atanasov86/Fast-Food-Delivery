package com.myNew.FastFoodDelivery.controller;

import com.myNew.FastFoodDelivery.converter.RestaurantConverter;
import com.myNew.FastFoodDelivery.dto.RestaurantDto;
import com.myNew.FastFoodDelivery.model.Restaurant;
import com.myNew.FastFoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantConverter restaurantConverter;

    @Autowired
    public RestaurantController(RestaurantService restaurantService,
                                RestaurantConverter restaurantConverter) {
        this.restaurantService = restaurantService;
        this.restaurantConverter = restaurantConverter;
    }

    public ResponseEntity<Set<RestaurantDto>> findAll() {
        Set<RestaurantDto> restaurantDtos = new HashSet<>();
        Set<Restaurant> restaurants = restaurantService.findAll();

        for (Restaurant restaurant : restaurants){
            RestaurantDto restaurantDto = restaurantConverter.toRestaurantDto(restaurant);
            restaurantDtos.add(restaurantDto);
        }
        return ResponseEntity.ok(restaurantDtos);
    }


}
