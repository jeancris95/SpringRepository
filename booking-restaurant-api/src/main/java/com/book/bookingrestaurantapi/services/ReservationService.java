package com.book.bookingrestaurantapi.services;

import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.jsons.CreateReservationRest;
import com.book.bookingrestaurantapi.jsons.ReservationRest;

public interface ReservationService {
		ReservationRest getReservation(Long reservationId) throws BookingException; 
		String createReservation(CreateReservationRest CreateReservationRest) throws BookingException;
}
