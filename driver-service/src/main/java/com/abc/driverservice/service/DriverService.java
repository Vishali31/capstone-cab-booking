package com.abc.driverservice.service;

import java.util.List;

import com.abc.driverservice.entity.Driver;



public interface DriverService {

	List<Driver> getAllDrivers();

	Driver saveDriver(Driver driverObj);

	Driver getDriverById(int diverId);

	void updateDriver(int driverId, Driver driver);

	void deleteDriver(int driverId);

	List<Driver> getAllBestDrivers();

}
