package com.abc.customerservice.entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@Column(name="customer_id", length=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	private int customerId;
	@Column( length=50)
	private String customerName;
	@Column(length=50)
	private String customerPassword;
	@Column(length=250)
	private String customerAddress;
	@Column(name="customer_mobile",length=20)
	private String mobile;
	@Column(name="customer_email",length=50)
	private String email;
	
	
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
	

}
