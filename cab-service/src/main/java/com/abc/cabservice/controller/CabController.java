package com.abc.cabservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import com.abc.cabservice.entity.Cab;
import com.abc.cabservice.model.SaveCab;
import com.abc.cabservice.service.CabService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cab")
public class CabController {
	
	@Autowired
	private CabService cabService;
	

	@GetMapping("/all")
	public List<Cab> fetchAllCabs() {
		List<Cab> cabs = cabService.getAllCabs();
		return cabs;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Cab> addAdmin(@Valid @RequestBody SaveCab newcab)  {
		Cab cabObj = new Cab();
		cabObj.setCabType(newcab.getCabType());
		cabObj.setCabDetail(newcab.getCabDetail());
		cabObj.setPerKmRate(newcab.getPerKmRate());
		cabObj.setSeatCount(newcab.getSeatCount());
		Cab cab = cabService.saveCab(cabObj);
		return new ResponseEntity<>(cab, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cab> fetchCabDetails(@PathVariable("id") int cabId) {
		Cab cab = cabService.getCabById(cabId);		 		
		return new ResponseEntity<>(cab,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> editCab(@PathVariable("id") int cabId, @RequestBody SaveCab cab) {
		Cab cabObj = new Cab();
		cabObj.setCabType(cab.getCabType());
		cabObj.setCabDetail(cab.getCabDetail());
		cabObj.setPerKmRate(cab.getPerKmRate());
		cabObj.setSeatCount(cab.getSeatCount());
		cabService.updateCab(cabId, cabObj);
	return new ResponseEntity<>("Cab detail updated successfully",HttpStatus.OK);

	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") int cabId) {
		cabService.deleteCab(cabId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/filter/{cabType}")
	public List<Cab> fetchCabByType(@PathVariable("cabType") String cabType) {
		List<Cab> cabList = cabService.viewCabsOfType(cabType);	 		
		return cabList;
	}
	@GetMapping("/count/{cabType}")
	public int fetchCountOfCabType(@PathVariable("cabType") String cabType) {
		int count = cabService.countByCabType(cabType);	 		
		return count;
	}
	
	
	

}
