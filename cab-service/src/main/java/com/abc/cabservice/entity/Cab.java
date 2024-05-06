package com.abc.cabservice.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name="cab")
public class Cab {

    @Id
    @Column( length=5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int cabId;
    @Column( length = 50)
	private String CabType;
	private float perKmRate;
	private int seatCount;
	@Column( length = 50)
	private String cabDetail;

	public String getCabDetail() {
		return cabDetail;
	}

	public void setCabDetail(String cabDetail) {
		this.cabDetail = cabDetail;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public String getCabType() {
		return CabType;
	}

	public void setCabType(String cabType) {
		CabType = cabType;
	}

	public float getPerKmRate() {
		return perKmRate;
	}

	public void setPerKmRate(float perKmRate) {
		this.perKmRate = perKmRate;
	}

}
