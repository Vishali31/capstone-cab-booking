package com.abc.adminservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@Column(length = 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int adminId;
	
	@NotNull(message = "Admin Name cannot be null")
	@NotBlank(message = "Admin Name cannot be empty")
	@Column(length = 20)
	private String adminName;
	
	@NotNull
	@NotBlank(message = "password cannot be empty")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Minimum eight characters, at least one letter and one number")
	@Column(length = 50)
	private String adminPassword;
	@Column(length = 250)
	private String adminAddress;
	
	@Column(length = 20)
	private String mobile;
	
	@Email()
	@Column(length = 50)
	private String email;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
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
