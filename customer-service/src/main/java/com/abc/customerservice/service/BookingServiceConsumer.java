package com.abc.customerservice.service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.customerservice.model.Booking;

@FeignClient(name = "BOOKING-SERVICE", url = "http://localhost:8085")
public interface BookingServiceConsumer {
	
	@GetMapping("/booking/{id}")
	ResponseEntity<Booking> getBookingDetails(@PathVariable("id") int bookingId);
}


