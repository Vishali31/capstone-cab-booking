package com.abc.driverservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.driverservice.dao.DriverDao;
import com.abc.driverservice.entity.Driver;
import com.abc.driverservice.exception.ResourceNotFoundException;
import com.abc.driverservice.model.ResponseDriver;

@Service
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverDao driverDao;

	@Override
	public List<Driver> getAllDrivers() {
		List<Driver> driver = driverDao.findAll();
		return driver;
	}
	
	@Override
	public List<Driver> getAllBestDrivers() {
		List<Driver> drivers = driverDao.findAllBestDrivers();
		return drivers;
	}

	@Override
	public Driver saveDriver(Driver driverObj) {
		Driver driver = driverDao.save(driverObj);
		return driver;
	}

	@Override
	public Driver getDriverById(int driverId) {

		Optional<Driver> optionalDriver = driverDao.findById(driverId);
		if (optionalDriver.isEmpty()) {
			throw new ResourceNotFoundException("driver not existing with id: " + driverId);
		}
		Driver Driver = optionalDriver.get();
		return Driver;
	}

	@Override
	public void updateDriver(int driverId, Driver driver) {
		Optional<Driver> optionalDriver = driverDao.findById(driverId);
		if (optionalDriver.isEmpty()) {
			throw new ResourceNotFoundException("driver not existing with id: " + driverId);
		}
		driverDao.save(driver);
		return;
	}

	@Override
	public void deleteDriver(int driverId) {
		Optional<Driver> optionalDriver = driverDao.findById(driverId);
		if (optionalDriver.isEmpty()) {
			throw new ResourceNotFoundException("driver not existing with id: " + driverId);
		}
		Driver driver = optionalDriver.get();
		driverDao.delete(driver);
	}

}
