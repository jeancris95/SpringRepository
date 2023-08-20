package com.book.bookingrestaurantapi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.book.bookingrestaurantapi.exceptions.NotFoundException;
import com.book.bookingrestaurantapi.repositories.ReservationRepository;
import com.book.bookingrestaurantapi.services.CancelReservationService;

public class CancelReservationServiceImpl implements CancelReservationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public String deleteReservation(String locator) throws BookingException {
		reservationRepository.findByLocator(locator)
				.orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));
		try {
			reservationRepository.deleteByLocator(locator);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("INTERNAL SERVER ERROR", "INTERNAL SERVER ERROR");
		}
		return "LOCATOR DELETE";
	}

}
