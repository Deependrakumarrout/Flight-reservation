package com.psa.flight_reservation_app.service;

import com.psa.flight_reservation_app.dto.ReservationRequest;
import com.psa.flight_reservation_app.entity.Reservation;

public interface ReservationService {
	Reservation bookFlight(ReservationRequest request);
}
