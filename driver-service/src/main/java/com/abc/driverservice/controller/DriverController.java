package com.abc.driverservice.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.abc.driverservice.entity.Driver;
import com.abc.driverservice.model.CabModel;
import com.abc.driverservice.model.ResponseDriver;
import com.abc.driverservice.model.SaveDriver;
import com.abc.driverservice.service.CabServiceConsumer;
import com.abc.driverservice.service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private CabServiceConsumer cabServiceConsumer;

	@GetMapping("/all")
	public List<ResponseDriver> fetchAllDrivers() {
		List<Driver> drivers = driverService.getAllDrivers();
		List<CabModel> cabs = cabServiceConsumer.getAllCabs();

		List<ResponseDriver> responseDrivers = new ArrayList<>();

		for (Driver driver : drivers) {
			
			Optional<CabModel> optionalCab =  cabs.stream()
	                   .filter(cab -> cab.getCabId() == driver.getCabId())
	                   .findFirst();
			ResponseDriver driverItem = new ResponseDriver().fromDriverEntity(driver);
			if (optionalCab.isPresent()) {
				CabModel findCab = optionalCab.get();
			    driverItem.setCab(findCab);
			}
			responseDrivers.add(driverItem);
		}
		return responseDrivers;

	}

	@GetMapping("/view-best-drivers")
	public List<ResponseDriver> viewBestDrivers() {
		List<Driver> bestDrivers = driverService.getAllBestDrivers();
		List<CabModel> cabs = cabServiceConsumer.getAllCabs();

		List<ResponseDriver> responseDrivers = new ArrayList<>();

		for (Driver driver : bestDrivers) {
			
			Optional<CabModel> optionalCab =  cabs.stream()
	                   .filter(cab -> cab.getCabId() == driver.getCabId())
	                   .findFirst();
			ResponseDriver driverItem = new ResponseDriver().fromDriverEntity(driver);
			if (optionalCab.isPresent()) {
				CabModel findCab = optionalCab.get();
			    driverItem.setCab(findCab);
			}
			responseDrivers.add(driverItem);
		}
		return responseDrivers;
	}

	@PostMapping("/save")
	public ResponseEntity<Driver> addDriver(@Valid @RequestBody SaveDriver SaveDriver) {
		Driver driverObj = new Driver();

		driverObj.setDriverName(SaveDriver.getDriverName());
		driverObj.setDriverPassword(SaveDriver.getDriverPassword());
		driverObj.setEmail(SaveDriver.getEmail());
		driverObj.setDriverAddress(SaveDriver.getDriverAddress());
		driverObj.setLicenseNo(SaveDriver.getLicenseNo());
		driverObj.setMobile(SaveDriver.getMobile());
		driverObj.setRating(SaveDriver.getRating());
		driverObj.setCabId(SaveDriver.getCabId());

		Driver driver = driverService.saveDriver(driverObj);
		return new ResponseEntity<>(driver, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDriver> fetchDriverDetails(@PathVariable("id") int driverId) {
		Driver driver = driverService.getDriverById(driverId);
		ResponseEntity<CabModel> response = cabServiceConsumer.getCabById(driver.getCabId());
		ResponseDriver driverObj = new ResponseDriver().fromDriverEntity(driver);

		if (response.hasBody()) {
			CabModel cab = response.getBody();
			driverObj.setCab(cab);
			return new ResponseEntity<>(driverObj, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or appropriate error handling
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> editDriver(@PathVariable("id") int driverId, @RequestBody SaveDriver driver) {
		Driver driverObj = new Driver();
		driverObj.setDriverName(driver.getDriverName());
		driverObj.setDriverId(driverId);
		driverObj.setCabId(driver.getCabId());
		driverObj.setDriverAddress(driver.getDriverAddress());
		driverObj.setDriverPassword(driver.getDriverPassword());
		driverObj.setEmail(driver.getEmail());
		driverObj.setLicenseNo(driver.getLicenseNo());
		driverObj.setRating(driver.getRating());
		driverObj.setMobile(driver.getMobile());
		driverService.updateDriver(driverId, driverObj);
		return new ResponseEntity<>("updated succesfully", HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable("id") int driverId) {
		driverService.deleteDriver(driverId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

}
