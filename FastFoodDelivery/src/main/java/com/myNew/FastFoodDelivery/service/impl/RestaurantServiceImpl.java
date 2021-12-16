package com.myNew.FastFoodDelivery.service.impl;

import com.myNew.FastFoodDelivery.exception.DublicateResourceException;
import com.myNew.FastFoodDelivery.exception.ThisDataIsNotFoundException;
import com.myNew.FastFoodDelivery.model.Restaurant;
import com.myNew.FastFoodDelivery.repository.RestaurantRepository;
import com.myNew.FastFoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Set<Restaurant> findAll() {
        return new HashSet<>(restaurantRepository.findAll());
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        try {
            return restaurantRepository.save(restaurant);
        } catch (DataIntegrityViolationException exception) {
            throw new DublicateResourceException
                    (String.format("Restaurant with name %d already exists!", restaurant.getRestaurantName()));
        }
    }

    @Override
    public Restaurant findByRestaurantName(String restaurantName) {
        return restaurantRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Restaurant witn %s doesn`t exists!", restaurantName)));
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ThisDataIsNotFoundException
                        (String.format("Restaurant with id %d doesn`t exists!", id)));
    }

    @Override
    public Restaurant update(Restaurant restaurant, Long id) {
        Restaurant foundRestaurant = findById(id);
        Restaurant updatedRestaurant = Restaurant.builder()
                .id(foundRestaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .build();

        return save(updatedRestaurant);
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }


}
