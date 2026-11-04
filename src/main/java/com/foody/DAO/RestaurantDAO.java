package com.foody.DAO;

import java.util.List;

import com.foody.model.Restaurant;

public interface RestaurantDAO {

	int addRestaurant(Restaurant restaurant);

	Restaurant getRestaurant(int restaurantId);

	int updateRestaurant(Restaurant restaurant);

	int deleteRestaurant(int restaurantId);

	List<Restaurant> getAllRestaurants();

}
