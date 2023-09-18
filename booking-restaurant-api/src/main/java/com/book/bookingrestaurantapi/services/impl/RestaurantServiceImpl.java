package com.book.bookingrestaurantapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.bookingrestaurantapi.entities.Restaurant;
import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.exceptions.NotFoundException;
import com.book.bookingrestaurantapi.jsons.RestaurantRest;
import com.book.bookingrestaurantapi.repositories.RestaurantRepository;
import com.book.bookingrestaurantapi.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public RestaurantRest getRestaurantById(Long restaurantId) throws BookingException {
		return modelMapper.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
	}

	private Restaurant getRestaurantEntity(Long restaurantId) throws BookingException {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT NOT FOUND"));
	}

	public List<RestaurantRest> getRestaurants() throws BookingException {
			final List<Restaurant>restaurantsEntity = restaurantRepository.findAll();
			return restaurantsEntity.stream().map(service ->modelMapper.map(service,RestaurantRest.class)).collect(Collectors.toList());
			
	}
}
