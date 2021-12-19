package com.myNew.FastFoodDelivery.service.impl;

import com.myNew.FastFoodDelivery.exception.DublicateResourceException;
import com.myNew.FastFoodDelivery.exception.ThisDataIsNotFoundException;
import com.myNew.FastFoodDelivery.model.Food;
import com.myNew.FastFoodDelivery.repository.FoodRepository;
import com.myNew.FastFoodDelivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    @Override
    public Food save(Food food) {
        try {
            return foodRepository.save(food);
        } catch (DataIntegrityViolationException exception) {
            throw new DublicateResourceException
                    (String.format("Food with name %d already exists!", food.getFoodName()));
        }
    }

    @Override
    public Food findByFoodName(String foodName) {
        return foodRepository.findByFoodName(foodName)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Food witn %s doesn`t exists!", foodName)));
    }

    @Override
    public Food findById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Food with id %d doesn`t exists!", id)));
    }

    @Override
    public Food update(Food food, Long id) {
        Food foundFood = findById(id);
        Food updatedFood = Food.builder()
                .id(foundFood.getId())
                .foodName(food.getFoodName())
                .build();

        return save(updatedFood);
    }

    @Override
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public Set<Food> findAll() {
        return new HashSet<>(foodRepository.findAll());
    }


        @Override
        public Food findByFoodRating(String foodRating) {
            return foodRepository.findByFoodName(foodRating)
                    .orElseThrow(() -> new ThisDataIsNotFoundException
                            (String.format("Food witn rating %s doesn`t exists!", foodRating)));
        }
    }



