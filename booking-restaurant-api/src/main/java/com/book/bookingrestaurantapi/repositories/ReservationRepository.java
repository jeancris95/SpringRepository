package com.book.bookingrestaurantapi.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.book.bookingrestaurantapi.entities.Reservation;
/*primer parametro generico dentro de  JpaRepository es decir reservation
	representa a la entidad sobre la cual se esta trabajando , y long
	que es el segundo parametro indica el tipo de dato de la clave primaria
*/
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	Optional<Reservation> findById(Long id);

	Optional<Reservation> findByLocator(String locator);

	@Modifying
	@Transactional
	Optional<Reservation> deleteByLocator(String locator);
	
	Optional<Reservation> findByTurnAndRestaurantId(String turn,Long restaurantId);
	
}
