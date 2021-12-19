package com.myNew.FastFoodDelivery.service;

import com.myNew.FastFoodDelivery.model.Restaurant;

import java.util.Set;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    Restaurant findByRestaurantName(String restaurantName);

    Restaurant findById (Long id);

    Restaurant update(Restaurant restaurant,Long id);

    void delete (Long id);

    Set<Restaurant> findAll() ;

    Restaurant findByLocation(String location);



}
