package com.myNew.FastFoodDelivery.controller;

import com.myNew.FastFoodDelivery.converter.FoodConverter;
import com.myNew.FastFoodDelivery.dto.FoodDto;
import com.myNew.FastFoodDelivery.model.Food;
import com.myNew.FastFoodDelivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/foods")
public class FoodController {

    private final FoodService foodService;
    private final FoodConverter foodConverter;

    @Autowired
    public FoodController(FoodService foodService, FoodConverter foodConverter) {
        this.foodService = foodService;
        this.foodConverter = foodConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FoodDto>> findAll() {
        return ResponseEntity.ok(foodService.findAll()
                .stream()
                .map(foodConverter::toFoodDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<FoodDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(foodConverter.toFoodDto(foodService.findById(id)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FoodDto> update(@RequestBody @Valid FoodDto foodDto,
                                          @PathVariable Long id) {
        Food food = foodConverter.toFood(foodDto);
        Food updatedFood = foodService.update(food, id);
        return ResponseEntity.ok(foodConverter.toFoodDto(updatedFood));
    }

    @PostMapping
    public ResponseEntity<FoodDto> save(@RequestBody @Valid FoodDto foodDto) {
        Food food = foodConverter.toFood(foodDto);
        Food savedFood = foodService.save(food);
        return ResponseEntity.ok(foodConverter.toFoodDto(savedFood));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        foodService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/foodRating/{foodRating}")
    public ResponseEntity<FoodDto> findByFoodRating(@PathVariable String foodRating) {
        return ResponseEntity.ok
                (foodConverter.toFoodDto(foodService.findByFoodRating(foodRating)));
    }
}
