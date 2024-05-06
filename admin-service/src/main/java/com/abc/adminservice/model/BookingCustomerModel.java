package com.abc.adminservice.model;

import java.util.List;



public class BookingCustomerModel {
	
	private int customerId;
	private String customerName;
	private String customerPassword;
	private String customerAddress;
	private String mobile;
	private String email;
	private List<BookingModel> bookings;
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<BookingModel> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingModel> bookings) {
		this.bookings = bookings;
	}
	
	

}
