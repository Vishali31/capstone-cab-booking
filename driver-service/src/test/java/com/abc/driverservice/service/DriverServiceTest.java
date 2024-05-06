package com.abc.driverservice.service;

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

import com.abc.driverservice.dao.DriverDao;
import com.abc.driverservice.entity.Driver;

@SpringBootTest
public class DriverServiceTest {

	@Mock
	private DriverDao driverDao;

	@InjectMocks
	private DriverServiceImpl driverService;

	@Test
	public void testSaveDriver() {
		Driver driverToSave = new Driver();

		when(driverDao.save(driverToSave)).thenReturn(driverToSave);

		Driver result = driverService.saveDriver(driverToSave);

		assertEquals(driverToSave, result);
	}

	@Test
	public void testGetAllDriver() {
		List<Driver> mockDriver = new ArrayList<>();

		when(driverDao.findAll()).thenReturn(mockDriver);

		List<Driver> result = driverService.getAllDrivers();

		assertEquals(mockDriver.size(), result.size());

	}

	@Test
	public void testGetDriverById() {
		int driverId = 1;
		Driver mockDriver = new Driver();

		Optional<Driver> optionalDriver = Optional.of(mockDriver);

		when(driverDao.findById(driverId)).thenReturn(optionalDriver);

		Driver result = driverService.getDriverById(driverId);

		assertEquals(mockDriver, result);
	}

	@Test
	public void testUpdateDriver() {
		int driverId = 1;
		Driver driverToUpdate = new Driver();

		Optional<Driver> optionalBooking = Optional.of(driverToUpdate);

		when(driverDao.findById(driverId)).thenReturn(optionalBooking);

		driverService.updateDriver(driverId, driverToUpdate);

		verify(driverDao).save(driverToUpdate);
	}

	@Test
	public void testDeleteDriver() {
		int driverId = 1;
		Driver driverToDelete = new Driver();

		Optional<Driver> optionalDriver = Optional.of(driverToDelete);

		when(driverDao.findById(driverId)).thenReturn(optionalDriver);

		driverService.deleteDriver(driverId);

		verify(driverDao).delete(driverToDelete);
	}

}
