package com.abc.cabservice.model;

public class SaveCab {

	private String cabType;
	private float perKmRate;
	private int seatCount;
	private String cabDetail;

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
