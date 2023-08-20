package com.book.bookingrestaurantapi.services;

import com.book.bookingrestaurantapi.exceptions.BookingException;

public interface CancelReservationService {
		public String deleteReservation(String locator) throws BookingException;
}
