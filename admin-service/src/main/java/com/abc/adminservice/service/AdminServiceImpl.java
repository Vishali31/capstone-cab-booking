package com.abc.adminservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.abc.adminservice.dao.AdminDao;
import com.abc.adminservice.entity.Admin;
import com.abc.adminservice.exception.ResourceNotFoundException;





@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	

	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> admin = adminDao.findAll();
		return admin;
	}

	@Override
	public Admin SaveAdmin(Admin adminObj) {
		Admin admin = adminDao.save(adminObj);
		return admin;
	}
	
	@Override
	public Admin getAdminById(int adminId) {
		Optional<Admin> optionalAdmin = adminDao.findById(adminId);
		if (optionalAdmin.isEmpty()) {
			throw new ResourceNotFoundException("Admin not existing with id: " +adminId );
		}
		Admin admin = optionalAdmin.get();
		return admin;
	}
	@Override
	public void updateAdmin(int adminId, Admin adminObj) {
		Optional<Admin> optionalAdmin = adminDao.findById(adminId);
		if (optionalAdmin.isEmpty()) {
			throw new ResourceNotFoundException("Admin not existing with id: " + adminId);
		}
		adminDao.save(adminObj);
		return;
	}
		

	@Override
	public void deleteAdmin(int adminId) {
		Optional<Admin> optionalAdmin = adminDao.findById(adminId);
		if (optionalAdmin.isEmpty()) {
			throw new ResourceNotFoundException("Admin not existing with id: " + adminId);
		}
		Admin admin = optionalAdmin.get();
		adminDao.delete(admin);
	}

}


