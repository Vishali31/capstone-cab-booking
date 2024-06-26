package com.abc.driverservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.driverservice.entity.Driver;

public interface DriverDao extends JpaRepository<Driver, Integer> {

	@Query("SELECT d FROM Driver d WHERE d.rating >= 4.5")
	List<Driver> findAllBestDrivers();
	

}
