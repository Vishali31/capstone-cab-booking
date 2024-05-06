package com.abc.bookingservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.bookingservice.entity.Booking;




public interface BookingDao extends JpaRepository<Booking,Integer>  {


	@Query("SELECT b FROM Booking b WHERE b.customerId=:customerId")
	List<Booking> getAllBookingsByCustomer(@Param("customerId") int customerId);
	
	@Query("SELECT b FROM Booking b WHERE b.driverId=:driverId")
	List<Booking> getAllBookingsByCab(@Param("driverId") int driverId);
	
	@Query("SELECT b FROM Booking b WHERE b.customerId=:customerId AND b.fromDate>=DATE(:start) AND b.todate<=DATE(:end)")
	List<Booking> getAllBookingsByDays(@Param("customerId") int customerId,@Param("start") String start,@Param("end") String end);
}
