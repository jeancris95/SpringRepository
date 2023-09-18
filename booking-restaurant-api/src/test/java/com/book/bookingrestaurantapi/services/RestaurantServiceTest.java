package com.book.bookingrestaurantapi.services;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.book.bookingrestaurantapi.entities.Board;
import com.book.bookingrestaurantapi.entities.Reservation;
import com.book.bookingrestaurantapi.entities.Restaurant;
import com.book.bookingrestaurantapi.entities.Turn;
import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.jsons.RestaurantRest;
import com.book.bookingrestaurantapi.repositories.RestaurantRepository;
import com.book.bookingrestaurantapi.services.impl.RestaurantServiceImpl;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class RestaurantServiceTest {
	private static final Long RESTAURANT_ID = 1L;
	public static final Restaurant RESTAURANT = new Restaurant();
	private static final String NAME = "Burger";
	private static final String DESCRIPTION = "The Best Burger";
	private static final String ADDRESS = "Plaza Pinazo";
	private static final String IMAGE = "www.image.com";
	public static List<Turn> TURN_LIST = new ArrayList<>();
	public static List<Board> Board_LIST = new ArrayList<>();
	public static List<Reservation> Reservation_LIST = new ArrayList<>();
	@Mock
	RestaurantRepository restaurantRepository;

	@InjectMocks
	RestaurantServiceImpl restaurantServiceImpl;

	@BeforeEach
	public void init() throws BookingException {
		MockitoAnnotations.openMocks(this);
		RESTAURANT.setName(NAME);
		RESTAURANT.setDescription(DESCRIPTION);
		RESTAURANT.setAddress(ADDRESS);
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setImage(IMAGE);
		RESTAURANT.setTurns(TURN_LIST);
		RESTAURANT.setBoards(Board_LIST);
		RESTAURANT.setReservations(Reservation_LIST);
	}

	@Test
	public void getRestaurantById() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
		restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
	}
	
	@Test
	public void getRestaurantByIdTestError()throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
		 assertThrows(BookingException.class, () -> {
		        restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
		    });
	}
	
	@Test
	public void getRestaurants() throws BookingException {
		final Restaurant restaurant=new Restaurant();
		Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
		final List<RestaurantRest>response=restaurantServiceImpl.getRestaurants();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}
}
