package com.abc.driverservice.service;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.driverservice.model.CabModel;





@FeignClient(name = "CAB-SERVICE", url = "http://localhost:8086" )
public interface CabServiceConsumer {
	
	@GetMapping("/cab/all")
	List<CabModel> getAllCabs();
	
	@GetMapping("/cab/{id}")
	ResponseEntity <CabModel> getCabById(@PathVariable("id") int cabId);
}


