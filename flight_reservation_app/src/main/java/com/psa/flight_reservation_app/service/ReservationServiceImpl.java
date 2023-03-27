package com.psa.flight_reservation_app.service;

import java.io.File;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.flight_reservation_app.dto.ReservationRequest;
import com.psa.flight_reservation_app.entity.Flight;
import com.psa.flight_reservation_app.entity.Passenger;
import com.psa.flight_reservation_app.entity.Reservation;
import com.psa.flight_reservation_app.repository.FlightRepository;
import com.psa.flight_reservation_app.repository.PassengerRepository;
import com.psa.flight_reservation_app.repository.ReservationRepository;
import com.psa.flight_reservation_app.utilities.EmailService;
import com.psa.flight_reservation_app.utilities.PDFgenerator;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationrepo;
	@Autowired
	private FlightRepository flightRepo;
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		passengerRepo.save(passenger);
		
		long flightId = request.getFlightId();
		Optional<Flight> findById=  flightRepo.findById(flightId);
		Flight flight = findById.get();
		
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		
		
		String fliePath = "D:\\sts v4.17Lover\\flight_reservation_app\\tickets\\reservation"+request.getFlightId()+".pdf";
		//System.out.println("reservation id: "+reservation.getId());
		reservationrepo.save(reservation);
		PDFgenerator pdf = new PDFgenerator();
	//	pdf.generatedPDF(fliePath, request.getFirstName(), request.getEmail(), request.getPhone(), flight.getOperatingAirlines(),flight.getDateOfDeparture(), flight.getDepartureCity(), flight.getArrivalCity());

		pdf.generateItineray(reservation, fliePath);
		emailService.sendItineray(passenger.getEmail(),fliePath);
		
		return reservation;
	}

}
