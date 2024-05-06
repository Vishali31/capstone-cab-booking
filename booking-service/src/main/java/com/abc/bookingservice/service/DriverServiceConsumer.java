package com.abc.bookingservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.abc.bookingservice.model.DriverModel;
import com.abc.bookingservice.model.ResponseDriver;

@FeignClient(name = "DRIVER-SERVICE", url = "http://localhost:8084")
public interface DriverServiceConsumer {

	@GetMapping("/driver/all")
	List<ResponseDriver> getAllDrivers();

	
	@GetMapping("/driver/{id}")
	ResponseEntity<DriverModel> getDriver(@PathVariable("id") int driverId);
}
