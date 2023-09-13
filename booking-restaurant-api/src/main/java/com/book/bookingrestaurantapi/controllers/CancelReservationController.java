package com.book.bookingrestaurantapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.responses.BookingResponse;
import com.book.bookingrestaurantapi.services.CancelReservationService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(path = "/booking-restaurant" + "/v1")
public class CancelReservationController {

	@Autowired
	CancelReservationService cancelReservationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/deleteReservation", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public BookingResponse<String> deleteReservation(@RequestParam String locator) throws BookingException {
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				cancelReservationService.deleteReservation(locator));
	}
}
