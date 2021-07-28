package com.javaeplanet.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaeplanet.flightreservation.dto.ReservationUpdateRequest;
import com.javaeplanet.flightreservation.entity.Reservation;
import com.javaeplanet.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {

	@Autowired
	private ReservationRepository reservationRepository;
	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationRestController.class);
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("findReservation()"+id);
		Reservation reservation = reservationRepository.findById(id).get();
		return reservation;
	}
	
	@RequestMapping("/currentreservations/{id}")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request,@PathVariable("id") Long id) {
		LOGGER.info("updateReservation() for"+request);
		Reservation reservation = reservationRepository.findById(id).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("saving reservation");
		return reservationRepository.save(reservation);
	}
}
