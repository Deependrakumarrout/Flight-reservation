package com.psa.flight_reservation_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psa.flight_reservation_app.dto.ReservationUpdateRequest;
import com.psa.flight_reservation_app.entity.Reservation;
import com.psa.flight_reservation_app.repository.ReservationRepository;

@RestController

public class ReservationRestController {

	// localhost:8080/flights/reservation/1
	

	@Autowired
	ReservationRepository reseRepo;
	
	@RequestMapping("/reservation/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		Optional<Reservation> findById =  reseRepo.findById(id); // return entity object
		Reservation reservation =  findById.get();				// now converting it into java object using get.
		return reservation;
	}
	
	//localhost:8080/flights/reservations
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> findById =  reseRepo.findById(request.getId());
		Reservation reservation =findById.get();
		reservation.setCheckedIn(request.isCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		return reseRepo.save(reservation);
		
		
	}
	
	
	@RequestMapping("/reservationDetails")
	public List<Reservation> consumeAll() {
		List<Reservation> findAll =  reseRepo.findAll();
		return findAll;
	}
	
	
//	@RequestMapping("/delete/{id}")
//	public void deleteReservation(@PathVariable("id") Long id) {
//		reseRepo.deleteById(id);
//	}
	
}
