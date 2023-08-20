package com.book.bookingrestaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.book.bookingrestaurantapi.entities.Restaurant;
import java.util.List;
import java.util.Optional;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	
		Optional<Restaurant>findById(Long id);
		Optional<Restaurant>findByName(String nameRestaurant);
		
		@Query("SELECT REST FROM Restaurant REST")
		public List<Restaurant> findRestaurants();
}
