package com.myNew.FastFoodDelivery.repository;

import com.myNew.FastFoodDelivery.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {


    Optional<Food> findByFoodName(String foodName);
}
