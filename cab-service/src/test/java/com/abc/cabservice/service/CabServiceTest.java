package com.abc.cabservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.cabservice.dao.CabDao;
import com.abc.cabservice.entity.Cab;
import com.abc.cabservice.exception.ResourceNotFoundException;
import com.abc.cabservice.service.CabService;
import com.abc.cabservice.service.CabServiceImpl;

@SpringBootTest
public class CabServiceTest {

	@Mock
	private CabDao cabDao;

	@InjectMocks
	private CabServiceImpl cabService;

	@Test
	public void testSaveCab() {

		Cab newCab = new Cab();
		newCab.setCabId(1);
		newCab.setCabDetail("TN-47");

		when(cabDao.save(newCab)).thenReturn(newCab);
		Cab savedCab = cabService.saveCab(newCab);
		assertEquals(newCab, savedCab);
	}

	@Test
	public void testGetAllCabs() {

		List<Cab> mockCabs = new ArrayList<>();
		mockCabs.add(createCab(1, "Sedan", 10.0f, 4, "Comfortable sedan"));
		mockCabs.add(createCab(2, "SUV", 15.0f, 6, "Spacious SUV"));

		when(cabDao.findAll()).thenReturn(mockCabs);

		List<Cab> result = cabService.getAllCabs();

		assertEquals(mockCabs.size(), result.size());
		for (int i = 0; i < mockCabs.size(); i++) {
			assertEquals(mockCabs.get(i), result.get(i));
		}

	}

	@Test
	public void testGetCabDetailsException() {
		when(cabDao.findById(100)).thenThrow(new ResourceNotFoundException("cab  not existing with id: 100"));
		assertThrows(ResourceNotFoundException.class, () -> cabService.getCabById(100));
	}

	private Cab createCab(int cabId, String cabType, float perKmRate, int seatCount, String cabDetail) {
		Cab cab = new Cab();
		cab.setCabId(cabId);
		cab.setCabType(cabType);
		cab.setPerKmRate(perKmRate);
		cab.setSeatCount(seatCount);
		cab.setCabDetail(cabDetail);
		return cab;
	}

	@Test
	public void testUpdateCab() {

		Cab cabToUpdate = new Cab();
		cabToUpdate.setCabId(102);
		cabToUpdate.setCabType("Sedan");
		cabToUpdate.setPerKmRate(10.0f);
		cabToUpdate.setSeatCount(4);
		cabToUpdate.setCabDetail("Comfortable sedan");

		when(cabDao.save(cabToUpdate)).thenReturn(cabToUpdate);

		boolean result = cabService.updateCab(102, cabToUpdate);

		assertEquals(true, result);

		verify(cabDao).save(cabToUpdate);
	}

	@Test
	public void testDeleteCab() {
		int cabIdToDelete = 102;
		Cab cabToDelete = new Cab();
		cabToDelete.setCabId(cabIdToDelete);

		boolean result = cabService.deleteCab(cabIdToDelete);

		assertEquals(true, result);

		verify(cabDao).deleteById(cabIdToDelete);
	}
}
