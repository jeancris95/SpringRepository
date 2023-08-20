package com.book.bookingrestaurantapi.services.impl;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.bookingrestaurantapi.entities.Reservation;
import com.book.bookingrestaurantapi.entities.Restaurant;
import com.book.bookingrestaurantapi.entities.Turn;
import com.book.bookingrestaurantapi.exceptions.BookingException;
import com.book.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.book.bookingrestaurantapi.exceptions.NotFoundException;
import com.book.bookingrestaurantapi.jsons.CreateReservationRest;
import com.book.bookingrestaurantapi.jsons.ReservationRest;
import com.book.bookingrestaurantapi.repositories.ReservationRepository;
import com.book.bookingrestaurantapi.repositories.RestaurantRepository;
import com.book.bookingrestaurantapi.repositories.TurnRepository;
import com.book.bookingrestaurantapi.services.ReservationService;
public class ReservationServiceImpl implements ReservationService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private TurnRepository TurnRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	public static final ModelMapper modelMapper = new ModelMapper();
	@Override
	public ReservationRest getReservation(Long reservationId) throws BookingException {
		 Reservation reserva = reservationRepository.getById(reservationId);
		    if (reserva == null) {
		        throw new NotFoundException("Reservation_NOT_FOUND", "Reservation not found");
		    }
		 ReservationRest reservationRest = modelMapper.map(reserva, ReservationRest.class);
		 return reservationRest;
	}
	


	@Override
	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
		//comprobamos si existe el id 
		final Restaurant restaurant = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("Restaurant_NOT_FOUND", "Restaurant_NOT_FOUND"));
		//comprobamos si existe el turno
		final Turn turn=TurnRepository.findById(createReservationRest.getTurnId()).orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));
		
		String locator = generateLocator(restaurant, createReservationRest);
		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurant);
		reservation.setTurn(turn.getName());
		
		try {
			reservationRepository.save(reservation);
		}catch (final Exception e) {
			// TODO: handle exception
			LOGGER.error("INTERNAL SERVER ERROR",e);
			throw new InternalServerErrorException("INTERNAL SERVER ERROR","INTERNAL SERVER ERROR");
		}
		return locator;
	}

	private String generateLocator(Restaurant restaurantId, CreateReservationRest CreateReservationRest)
			throws BookingException {
		return restaurantId.getName() + CreateReservationRest.getTurnId();
	}
	
}
