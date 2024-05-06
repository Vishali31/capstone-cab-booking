package com.abc.adminservice.model;

import java.util.List;


public class BookingCabModel {
	
	
	private int cabId;
	private String cabType;
	private float perKmRate;
	private int seatCount;
	private String cabDetail;
	private List<BookingModel> bookings;
	
	
	public int getCabId() {
		return cabId;
	}
	public void setCabId(int cabId) {
		this.cabId = cabId;
	}
	public String getCabType() {
		return cabType;
	}
	public void setCabType(String cabType) {
		this.cabType = cabType;
	}
	public float getPerKmRate() {
		return perKmRate;
	}
	public void setPerKmRate(float perKmRate) {
		this.perKmRate = perKmRate;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	public String getCabDetail() {
		return cabDetail;
	}
	public void setCabDetail(String cabDetail) {
		this.cabDetail = cabDetail;
	}
	public List<BookingModel> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingModel> bookings) {
		this.bookings = bookings;
	}
	
	
	

}


