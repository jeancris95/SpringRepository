package com.book.bookingrestaurantapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.book.bookingrestaurantapi.controllers.RestaurantController;
import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.jsons.RestaurantRest;
import com.book.bookingrestaurantapi.jsons.TurnRest;
import com.book.bookingrestaurantapi.responses.BookingResponse;
import com.book.bookingrestaurantapi.services.RestaurantService;

public class ResturantControllerTest {
	private static final Long RESTAURANT_ID = 1L;

	private static final String NAME = "Burger";
	private static final String DESCRIPTION = "The Best Burger";
	private static final String ADDRESS = "Plaza Pinazo";
	private static final String IMAGE = "www.image.com";

	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200 OK";
	private static final String OK = "OK";
	public static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
	public static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();;
	public static List<TurnRest> TURN_LIST = new ArrayList<>();

	@Mock
	RestaurantService restaurantService;

	@InjectMocks
	RestaurantController restaurantController;

	@BeforeEach
	public void init() throws BookingException {
		MockitoAnnotations.openMocks(this);

		RESTAURANT_REST.setName(NAME);
		RESTAURANT_REST.setDescription(DESCRIPTION);
		RESTAURANT_REST.setAddress(ADDRESS);
		RESTAURANT_REST.setId(RESTAURANT_ID);
		RESTAURANT_REST.setImage(IMAGE);
		RESTAURANT_REST.setTurns(TURN_LIST);

		Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
	}

	@Test
	public void getRestaurantByIdTest() throws BookingException {
		final BookingResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);

		assertEquals(response.getData(), RESTAURANT_REST);
	}

	@Test
	public void getRestaurantsTest() throws BookingException {
		final BookingResponse<List<RestaurantRest>> response = restaurantController.gestRestaurants();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST_LIST);
	}
}
