package com.abc.bookingservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.abc.bookingservice.entity.Booking;




public interface BookingService {

	List<Booking> getAllBookings();

	Booking SaveBooking(Booking bookingObj);

	Booking getBookingById(int bookingId);

	
	void deleteBooking(int bookingId);

	List<Booking> getAllBookingsByCustomer(int customerId);

	List<Booking> getAllBookingsByCabId(int cabId);

	LocalDateTime dateFormatter(String dateString);

	void updateBooking(int bookingId, Booking bookingObj);


	String dateFormat(LocalDateTime date, String Pattern);


	List<Booking> getAllBookingsByDays(int customerId, String start, String end);


}
