package com.abc.bookingservice.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.bookingservice.dao.BookingDao;
import com.abc.bookingservice.entity.Booking;
import com.abc.bookingservice.exception.ResourceNotFoundException;


@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
		


	@Override
	public List<Booking> getAllBookings() {
		List<Booking> booking = bookingDao.findAll();
		return booking;
	}
	@Override
	public List<Booking> getAllBookingsByDays(int customerId,String start,String end) {
		List<Booking> booking = bookingDao.getAllBookingsByDays(customerId,start,end);
		return booking;
	}

	@Override
	public List<Booking> getAllBookingsByCustomer(int customerId) {
		List<Booking> booking = bookingDao.getAllBookingsByCustomer(customerId);
		return booking;
	}

	@Override
	public List<Booking> getAllBookingsByCabId(int driverId) {
		List<Booking> booking = bookingDao.getAllBookingsByCab(driverId);
		return booking;
	}

	@Override
	public Booking SaveBooking(Booking bookingObj) {
		Booking booking = bookingDao.save(bookingObj);
		return booking;
	}

	@Override
	public Booking getBookingById(int bookingId) {
		Optional<Booking> optionalBooking = bookingDao.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			throw new ResourceNotFoundException("booking not existing with id: " + bookingId);
		}
		Booking booking = optionalBooking.get();
		return booking;
	}

	@Override
	public void updateBooking(int bookingId, Booking bookingObj) {
		Optional<Booking> optionalBooking = bookingDao.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			throw new ResourceNotFoundException("booking not existing with id: " + bookingId);
		}
		bookingDao.save(bookingObj);
		return;
	}

	@Override
	public void deleteBooking(int bookingId) {
		Optional<Booking> optionalBooking = bookingDao.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			throw new ResourceNotFoundException("customer not existing with id: " + bookingId);
		}
		Booking booking = optionalBooking.get();
		bookingDao.delete(booking);
	}
	@Override
	public LocalDateTime dateFormatter(String dateString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime;
	}
	@Override
	public String dateFormat(LocalDateTime date,String pattern) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String formattedDate = formatter.format(date);
		return formattedDate;
	}

}
