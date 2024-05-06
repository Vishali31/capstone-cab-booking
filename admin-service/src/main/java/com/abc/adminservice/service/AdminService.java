package com.abc.adminservice.service;

import java.util.List;

import com.abc.adminservice.entity.Admin;

public interface AdminService {

	
	
	List<Admin> getAllAdmin();
	
	Admin SaveAdmin(Admin adminObj);

	Admin getAdminById(int adminId);

	void updateAdmin(int adminId, Admin adminObj);

	void deleteAdmin(int adminId);
}
