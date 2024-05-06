package com.abc.adminservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.adminservice.dao.AdminDao;
import com.abc.adminservice.entity.Admin;

@SpringBootTest
public class AdminServiceTest {

	@Mock
	private AdminDao adminDao;

	@InjectMocks
	private AdminServiceImpl adminService;

	@Test
	public void testSaveAdmin() {
		Admin adminToSave = new Admin();

		when(adminDao.save(adminToSave)).thenReturn(adminToSave);

		Admin result = adminService.SaveAdmin(adminToSave);

		assertEquals(adminToSave, result);
	}

	@Test
	public void testGetAllAdmins() {
		List<Admin> mockAdmins = new ArrayList<>();

		when(adminDao.findAll()).thenReturn(mockAdmins);

		List<Admin> result = adminService.getAllAdmin();

		assertEquals(mockAdmins.size(), result.size());

	}

	@Test
	public void testGetAdminById() {
		int adminId = 1;
		Admin mockAdmin = new Admin();

		Optional<Admin> optionaladmin = Optional.of(mockAdmin);

		when(adminDao.findById(adminId)).thenReturn(optionaladmin);

		Admin result = adminService.getAdminById(adminId);

		assertEquals(mockAdmin, result);
	}

	@Test
	public void testUpdateAdmin() {
		int adminId = 1;
		Admin adminToUpdate = new Admin();

		Optional<Admin> optionaladmin = Optional.of(adminToUpdate);

		when(adminDao.findById(adminId)).thenReturn(optionaladmin);

		adminService.updateAdmin(adminId, adminToUpdate);

		verify(adminDao).save(adminToUpdate);
	}

	@Test
	public void testDeleteAdmin() {
		int adminId = 1;
		Admin adminToDelete = new Admin();

		Optional<Admin> optionaladmin = Optional.of(adminToDelete);

		when(adminDao.findById(adminId)).thenReturn(optionaladmin);

		adminService.deleteAdmin(adminId);

		verify(adminDao).delete(adminToDelete);
	}

}
