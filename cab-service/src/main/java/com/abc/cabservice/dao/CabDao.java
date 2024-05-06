package com.abc.cabservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.cabservice.entity.Cab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface CabDao extends JpaRepository<Cab,Integer>  {

	@Query("SELECT c FROM Cab c WHERE LOWER(c.CabType)=LOWER(:cabType)")
	List<Cab> findByCabType(@Param("cabType") String cabType);
	@Query("select COUNT(*) from Cab c WHERE LOWER(c.CabType)=LOWER(:cabType)")
	int countByCabType(@Param("cabType") String cabType);
	
	
}
