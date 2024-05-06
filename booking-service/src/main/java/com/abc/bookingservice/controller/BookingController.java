package com.abc.bookingservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abc.bookingservice.entity.Booking;
import com.abc.bookingservice.exception.ResourceNotFoundException;
import com.abc.bookingservice.model.BookingCabModel;
import com.abc.bookingservice.model.BookingCustomerModel;
import com.abc.bookingservice.model.CabModel;
import com.abc.bookingservice.model.CustomerModel;
import com.abc.bookingservice.model.DriverModel;
import com.abc.bookingservice.model.ResponseBooking;
import com.abc.bookingservice.model.ResponseDriver;
import com.abc.bookingservice.model.SaveBooking;

import com.abc.bookingservice.service.BookingServiceImpl;
import com.abc.bookingservice.service.CabServiceConsumer;
import com.abc.bookingservice.service.CustomerServiceConsumer;
import com.abc.bookingservice.service.DriverServiceConsumer;

import feign.FeignException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingServiceImpl bookingService;

	@Autowired
	private CabServiceConsumer cabServiceConsumer;

	@Autowired
	private CustomerServiceConsumer customerServiceConsumer;

	@Autowired
	private DriverServiceConsumer driverServiceConsumer;

	@GetMapping("/all")
	public List<Booking> fetchAllBookings() {
		List<Booking> booking = bookingService.getAllBookings();
		return booking;

	}

	@GetMapping("/{customer_id}/trips")
	public List<Booking> getAllTripsByCustomer(@PathVariable("customer_id") int customerId) {
		List<Booking> booking = bookingService.getAllBookingsByCustomer(customerId);
		return booking;

	}

	@GetMapping("/trips-by-cabwise")
	public List<BookingCabModel> getAllTripsByCabwise() {
		List<CabModel> cabs = cabServiceConsumer.getAllCabs();
		List<Booking> bookingItems = bookingService.getAllBookings();
		List<ResponseDriver> drivers = driverServiceConsumer.getAllDrivers();

		List<BookingCabModel> bookings = new ArrayList<>();

		for (CabModel cab : cabs) {
			BookingCabModel bookingCabResp = new BookingCabModel();
			List<Booking> bookingForCab = new ArrayList<Booking>();
			Optional<ResponseDriver> driver = drivers.stream().filter(dri -> dri.getCab().getCabId() == cab.getCabId())
					.findFirst();
			if (driver.isPresent()) {
				bookingForCab = bookingItems.stream()
						.filter(book -> book.getDriverId() == driver.get().getDriverId()).toList();
			}
			bookingCabResp.setBookings(bookingForCab);

			bookingCabResp.setCabDetail(cab.getCabDetail());
			bookingCabResp.setCabId(cab.getCabId());
			bookingCabResp.setCabType(cab.getCabType());
			bookingCabResp.setPerKmRate(cab.getPerKmRate());
			bookingCabResp.setSeatCount(cab.getSeatCount());

			bookings.add(bookingCabResp);
		}

		return bookings;
	}

	@GetMapping("/trips-by-customerwise")
	public List<BookingCustomerModel> getAllTripsByCustomerwise() {

		List<CustomerModel> customers = customerServiceConsumer.getAllCustomers();

		List<BookingCustomerModel> bookings = new ArrayList<>();

		for (CustomerModel customer : customers) {
			List<Booking> bookingItems = bookingService.getAllBookingsByCustomer(customer.getCustomerId());

			BookingCustomerModel bookingCusResp = new BookingCustomerModel();

			bookingCusResp.setBookings(bookingItems);

			bookingCusResp.setCustomerId(customer.getCustomerId());
			bookingCusResp.setCustomerAddress(customer.getCustomerAddress());
			bookingCusResp.setCustomerName(customer.getCustomerName());
			bookingCusResp.setEmail(customer.getEmail());
			bookingCusResp.setMobile(customer.getMobile());

			bookings.add(bookingCusResp);
		}

		return bookings;

	}

	@GetMapping("/trips-by-datewise")
	public Map<String, List<ResponseBooking>> getAllTripsByDatewise() {

		List<CustomerModel> customers = customerServiceConsumer.getAllCustomers();
		List<Booking> allBookings = bookingService.getAllBookings();
		List<ResponseDriver> allDrivers = driverServiceConsumer.getAllDrivers();

		Map<String, List<ResponseBooking>> hashMap = new HashMap<String, List<ResponseBooking>>();

		for (Booking booking : allBookings) {
			Optional<CustomerModel> optionalCustomer = customers.stream()
					.filter(customer -> customer.getCustomerId() == booking.getCustomerId()).findFirst();
			Optional<ResponseDriver> optionalDriver = allDrivers.stream()
					.filter(driver -> driver.getDriverId() == booking.getDriverId()).findFirst();
			ResponseBooking bookingItem = new ResponseBooking().fromResponseBookingEntity(booking);
			if (optionalCustomer.isPresent()) {
				CustomerModel findCustomer = optionalCustomer.get();
				bookingItem.setCustomer(findCustomer);
			}
			if (optionalDriver.isPresent()) {
				ResponseDriver findDriver = optionalDriver.get();
				bookingItem.setDriver(findDriver);
			}
			String date = bookingService.dateFormat(booking.getCreatedAt(), "yyyy-MM-dd");
			if (!hashMap.containsKey(date)) {
				List<ResponseBooking> bookings = new ArrayList<ResponseBooking>();
				bookings.add(bookingItem);

				hashMap.put(date, bookings);
			} else {
				hashMap.get(date).add(bookingItem);
			}
		}

		return hashMap;

	}

	@GetMapping("/trips-by-days/{customer_id}")
	public List<ResponseBooking> getAllTripsByDays(@PathVariable("customer_id") int customerId,
			@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {

		List<CustomerModel> customers = customerServiceConsumer.getAllCustomers();
		List<Booking> allBookings = bookingService.getAllBookingsByDays(customerId, start, end);
		List<ResponseDriver> allDrivers = driverServiceConsumer.getAllDrivers();

		List<ResponseBooking> bookings = new ArrayList<ResponseBooking>();

		for (Booking booking : allBookings) {
			Optional<CustomerModel> optionalCustomer = customers.stream()
					.filter(customer -> customer.getCustomerId() == booking.getCustomerId()).findFirst();
			Optional<ResponseDriver> optionalDriver = allDrivers.stream()
					.filter(driver -> driver.getDriverId() == booking.getDriverId()).findFirst();
			ResponseBooking bookingItem = new ResponseBooking().fromResponseBookingEntity(booking);
			if (optionalCustomer.isPresent()) {
				CustomerModel findCustomer = optionalCustomer.get();
				bookingItem.setCustomer(findCustomer);
			}
			if (optionalDriver.isPresent()) {
				ResponseDriver findDriver = optionalDriver.get();
				bookingItem.setDriver(findDriver);
			}

			bookings.add(bookingItem);

		}

		return bookings;

	}

	@PostMapping("/save")
	public ResponseEntity<Booking> addBooking(@Valid @RequestBody SaveBooking saveBooking) {

		try {

			Booking bookingObj = new Booking();

			ResponseEntity<DriverModel> response = driverServiceConsumer.getDriver(saveBooking.getDriverId());
			DriverModel driver = response.getBody();
			ResponseEntity<CabModel> responseCab = cabServiceConsumer.getCabById(driver.getCabId());
			if (responseCab.hasBody()) {
				CabModel cab = responseCab.getBody();
				double price = cab.getPerKmRate() * saveBooking.getDistanceInKm();
				bookingObj.setPrice(price);
			}

			bookingObj.setCustomerId(saveBooking.getCustomerId());
			bookingObj.setDriverId(saveBooking.getDriverId());
			bookingObj.setSource(saveBooking.getSource());
			bookingObj.setDestination(saveBooking.getDestination());
			bookingObj.setFromDate(bookingService.dateFormatter(saveBooking.getFromDate()));
			bookingObj.setTodate(bookingService.dateFormatter(saveBooking.getTodate()));
			bookingObj.setDistanceInKm(saveBooking.getDistanceInKm());
			bookingObj.setBookingStatus(1);

			Booking booking = bookingService.SaveBooking(bookingObj);
			return new ResponseEntity<>(booking, HttpStatus.CREATED);
		} catch (FeignException.NotFound exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage());
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage());
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> fetchBookingDetails(@PathVariable("id") int bookingId) {
		Booking booking = bookingService.getBookingById(bookingId);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	@GetMapping("/calculate-bill/{booking_id}")
	public ResponseEntity<String> fetchDetails(@PathVariable("booking_id") int bookingId) {
		try {
			Booking booking = bookingService.getBookingById(bookingId);

			return new ResponseEntity<>("Your trip cost : " + booking.getPrice(), HttpStatus.OK);
		} catch (Exception exc) {

			throw new ResourceNotFoundException("Booking not existing with id: " + bookingId);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> editBooking(@PathVariable("id") int bookingId, @RequestBody SaveBooking booking) {
		Booking bookingObj = new Booking();
		bookingObj.setCustomerId(booking.getCustomerId());
		bookingObj.setBookingId(bookingId);
		bookingObj.setSource(booking.getSource());
		bookingObj.setDestination(booking.getDestination());

		bookingObj.setDriverId(booking.getDriverId());
		bookingObj.setFromDate(bookingService.dateFormatter(booking.getFromDate()));
		bookingObj.setTodate(bookingService.dateFormatter(booking.getFromDate()));

		bookingService.updateBooking(bookingId, bookingObj);
		return new ResponseEntity<>("updated succesfully", HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable("id") int bookingId) {
		bookingService.deleteBooking(bookingId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

}
