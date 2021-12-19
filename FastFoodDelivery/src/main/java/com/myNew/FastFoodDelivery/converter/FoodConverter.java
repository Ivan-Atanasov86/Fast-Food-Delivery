package com.myNew.FastFoodDelivery.converter;

import com.myNew.FastFoodDelivery.dto.FoodDto;
import com.myNew.FastFoodDelivery.model.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodConverter {

    public FoodDto toFoodDto(Food food) {
        return FoodDto.builder()
                .foodRating(food.getFoodRating())
                .build();
    }

    public Food toFood(FoodDto foodDto) {
        return Food.builder()
                .foodRating(foodDto.getFoodRating())
                .build();

    }

}
