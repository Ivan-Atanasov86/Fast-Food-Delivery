package com.myNew.FastFoodDelivery.controller;

import com.myNew.FastFoodDelivery.converter.RestaurantConverter;
import com.myNew.FastFoodDelivery.dto.RestaurantDto;
import com.myNew.FastFoodDelivery.model.Restaurant;
import com.myNew.FastFoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantConverter restaurantConverter;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantConverter restaurantConverter) {
        this.restaurantService = restaurantService;
        this.restaurantConverter = restaurantConverter;
    }

    @GetMapping
    public ResponseEntity<Set<RestaurantDto>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll()
                .stream()
                .map(restaurantConverter::toRestaurantDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<RestaurantDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantConverter.toRestaurantDto(restaurantService.findById(id)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RestaurantDto> update(@RequestBody @Valid RestaurantDto restaurantDto,
                                                @PathVariable Long id) {
        Restaurant restaurant = restaurantConverter.toRestaurant(restaurantDto);
        Restaurant updatedRestaurant = restaurantService.update(restaurant, id);
        return ResponseEntity.ok(restaurantConverter.toRestaurantDto(updatedRestaurant));
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> save(@RequestBody @Valid RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantConverter.toRestaurant(restaurantDto);
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        return ResponseEntity.ok(restaurantConverter.toRestaurantDto(savedRestaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/restaurantName/{restaurantName}")
    public ResponseEntity<RestaurantDto> findByRestaurantName(@PathVariable String restaurantName) {
        return ResponseEntity.ok
                (restaurantConverter.toRestaurantDto(restaurantService.findByRestaurantName(restaurantName)));
    }
}
