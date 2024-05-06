package com.abc.customerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.customerservice.entity.Customer;




public interface CustomerDao extends JpaRepository<Customer,Integer> {
	
	
	@Query("SELECT c FROM Customer c WHERE c.email=:email")
	Customer findByEmail(@Param("email") String email);
	
	
	

}
