package com.book.bookingrestaurantapi.services;

import java.util.List;

import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.jsons.RestaurantRest;

public interface RestaurantService {
	RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;
	public List<RestaurantRest> getRestaurants() throws BookingException;
	
}
