package com.abc.bookingservice.model;

public class CabModel {
	
	private int cabId;
	private String cabType;
	private float perKmRate;
	private int seatCount;
	private String cabDetail;
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
	

}
