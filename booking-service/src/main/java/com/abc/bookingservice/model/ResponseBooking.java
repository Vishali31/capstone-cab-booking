package com.abc.bookingservice.model;

import java.time.LocalDateTime;

import com.abc.bookingservice.entity.Booking;



public class ResponseBooking {

	private int bookingId;
	private int customerId;
	private int driverId;
	private String source;
	private String destination;
	private LocalDateTime fromDate;
	private LocalDateTime todate;
	private int bookingStatus;
	private double price;
	private float distanceInKm;
	private LocalDateTime createdAt;
	 private LocalDateTime updatedAt;
	private CustomerModel customer;
	private ResponseDriver driver;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getTodate() {
		return todate;
	}

	public void setTodate(LocalDateTime todate) {
		this.todate = todate;
	}

	public int getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public float getDistanceInKm() {
		return distanceInKm;
	}

	public void setDistanceInKm(float distanceInKm) {
		this.distanceInKm = distanceInKm;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public ResponseDriver getDriver() {
		return driver;
	}

	public void setDriver(ResponseDriver driver) {
		this.driver = driver;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ResponseBooking fromResponseBookingEntity(Booking booking) {
		ResponseBooking responseBooking = new ResponseBooking();
		responseBooking.setBookingId(booking.getBookingId());
        responseBooking.setBookingStatus(booking.getBookingStatus());
        responseBooking.setCustomerId(booking.getCustomerId());
        responseBooking.setDestination(booking.getDestination());
        responseBooking.setDistanceInKm(booking.getDistanceInKm());
        responseBooking.setDriverId(booking.getDriverId());
        responseBooking.setFromDate(booking.getFromDate());
        responseBooking.setPrice(booking.getPrice());
        responseBooking.setSource(booking.getSource());
        responseBooking.setTodate(booking.getTodate());
        responseBooking.setCreatedAt(booking.getCreatedAt());
        responseBooking.setUpdatedAt(booking.getUpdatedAt());
        
        return responseBooking;
    }

}
