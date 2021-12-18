package com.myNew.FastFoodDelivery.service;

import com.myNew.FastFoodDelivery.model.Food;
import com.myNew.FastFoodDelivery.model.Restaurant;

import java.util.Set;

public interface FoodService {

    Food save(Food food);

    Food findByFoodName(String foodName);

    Food findById (Long id);

    Food update(Food food,Long id);

    void delete (Long id);

    Set<Food> findAll() ;

    Food findByRating (String foodRating);
}
