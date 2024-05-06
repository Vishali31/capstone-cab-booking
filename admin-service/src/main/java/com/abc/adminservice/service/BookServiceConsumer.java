package com.abc.adminservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.adminservice.model.BookingCabModel;
import com.abc.adminservice.model.BookingCustomerModel;
import com.abc.adminservice.model.BookingModel;
import com.abc.adminservice.model.ResponseBooking;

@FeignClient(name = "BOOKING-SERVICE", url = "http://localhost:8085" )
public interface BookServiceConsumer {

	
	@GetMapping("/booking/{customer_id}/trips")
	public List<BookingModel> getAllTripsByCustomer(@PathVariable("customer_id") int customerId) ;
	
	@GetMapping("/booking/trips-by-cabwise")
	public List<BookingCabModel> getAllTripsByCabwise();
	
	@GetMapping("/booking/trips-by-customerwise")
	public List<BookingCustomerModel> getAllTripsByCustomerwise();
	
	@GetMapping("/booking/trips-by-datewise")
	public Map<String, List<ResponseBooking>> getAllTripsByDatewise();
	
	@GetMapping("/booking/trips-by-days/{customer_id}")
	public List<ResponseBooking> getAllTripsByDays(@PathVariable("customer_id") int customerId,
			@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) ;

}
