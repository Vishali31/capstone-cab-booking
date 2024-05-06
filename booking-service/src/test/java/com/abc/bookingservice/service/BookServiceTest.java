package com.abc.bookingservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.bookingservice.dao.BookingDao;
import com.abc.bookingservice.entity.Booking;

@SpringBootTest
public class BookServiceTest {

	@Mock
	private BookingDao bookDao;

	@InjectMocks
	private BookingServiceImpl bookingService;

	@Test
	public void testSaveBooking() {
		Booking bookingToSave = new Booking();

		when(bookDao.save(bookingToSave)).thenReturn(bookingToSave);

		Booking result = bookingService.SaveBooking(bookingToSave);

		assertEquals(bookingToSave, result);
	}

	@Test
	public void testGetAllBookings() {
		List<Booking> mockBookings = new ArrayList<>();

		when(bookDao.findAll()).thenReturn(mockBookings);

		List<Booking> result = bookingService.getAllBookings();

		assertEquals(mockBookings.size(), result.size());

	}

	@Test
	public void testGetBookingById() {
		int bookingId = 1;
		Booking mockBooking = new Booking();

		Optional<Booking> optionalBooking = Optional.of(mockBooking);

		when(bookDao.findById(bookingId)).thenReturn(optionalBooking);

		Booking result = bookingService.getBookingById(bookingId);

		assertEquals(mockBooking, result);
	}

	@Test
	public void testUpdateBooking() {
		int bookingId = 1;
		Booking bookingToUpdate = new Booking();

		Optional<Booking> optionalBooking = Optional.of(bookingToUpdate);

		when(bookDao.findById(bookingId)).thenReturn(optionalBooking);

		bookingService.updateBooking(bookingId, bookingToUpdate);

		verify(bookDao).save(bookingToUpdate);
	}

	@Test
	public void testDeleteBooking() {
		int bookingId = 1;
		Booking bookingToDelete = new Booking();

		Optional<Booking> optionalBooking = Optional.of(bookingToDelete);

		when(bookDao.findById(bookingId)).thenReturn(optionalBooking);

		bookingService.deleteBooking(bookingId);

		verify(bookDao).delete(bookingToDelete);
	}

}
