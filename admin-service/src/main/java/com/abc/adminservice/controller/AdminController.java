package com.abc.adminservice.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.adminservice.entity.Admin;
import com.abc.adminservice.model.BookingCabModel;
import com.abc.adminservice.model.BookingCustomerModel;
import com.abc.adminservice.model.BookingModel;
import com.abc.adminservice.model.ResponseBooking;
import com.abc.adminservice.model.SaveAdmin;
import com.abc.adminservice.service.AdminService;
import com.abc.adminservice.service.BookServiceConsumer;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BookServiceConsumer bookServiceConsumer;
	
	@GetMapping("/{customer_id}/trips")
	public List<BookingModel> getAllTripsByCustomer(@PathVariable("customer_id") int customerId) {
		List<BookingModel> booking = bookServiceConsumer.getAllTripsByCustomer(customerId);
		return booking;

	}
	@GetMapping("/trips-by-cabwise")
	public List<BookingCabModel> getAllTripsByCabwise(){
		
		List<BookingCabModel> booking = bookServiceConsumer.getAllTripsByCabwise();
		return booking;
		
	}
	@GetMapping("/trips-by-customerwise")
	public List<BookingCustomerModel> getAllTripsByCustomerwise() {
		List<BookingCustomerModel> booking = bookServiceConsumer.getAllTripsByCustomerwise();
		return booking;
	}
	@GetMapping("/trips-by-datewise")
	public Map<String, List<ResponseBooking>> getAllTripsByDatewise() {
		Map<String, List<ResponseBooking>> booking = bookServiceConsumer.getAllTripsByDatewise();
		return booking;
	}
	@GetMapping("/trips-by-days/{customer_id}")
	public List<ResponseBooking> getAllTripsByDays(@PathVariable("customer_id") int customerId,
			@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {

		List<ResponseBooking> booking = bookServiceConsumer.getAllTripsByDays(customerId, start, end);
		return booking;
	}
	
	
	
	@GetMapping("/all")
	public List<Admin> fetchAllAdmin() {
		List<Admin> admin = adminService.getAllAdmin();
		return admin;
	}
	@PostMapping("/save")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin saveAdmin) {
		Admin adminObj = new Admin();

		adminObj.setAdminName(saveAdmin.getAdminName());
		adminObj.setAdminPassword(saveAdmin.getAdminPassword());
		adminObj.setAdminAddress(saveAdmin.getAdminAddress());
		adminObj.setEmail(saveAdmin.getEmail());
		adminObj.setMobile(saveAdmin.getMobile());
		
		
		Admin admin = adminService.SaveAdmin(adminObj);
		return new ResponseEntity<>(admin, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Admin> fetchAdminDetails(@PathVariable("id") int adminId) {
		Admin admin = adminService.getAdminById(adminId);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<String> editAdmin(@PathVariable("id") int adminId, @RequestBody Admin admin ) {
		Admin adminObj = new Admin();
		adminObj.setAdminName(admin.getAdminName());
		adminObj.setAdminId(adminId);
		adminObj.setAdminAddress(admin.getAdminAddress());
		adminObj.setAdminPassword(admin.getAdminPassword());
		adminObj.setEmail(admin.getEmail());
		adminObj.setMobile(admin.getMobile());
		
		adminService.updateAdmin(adminId, adminObj);
		return new ResponseEntity<>("updated succesfully", HttpStatus.CREATED);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable("id") int adminId) {
		adminService.deleteAdmin(adminId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

	

}
