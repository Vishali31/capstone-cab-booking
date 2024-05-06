package com.abc.cabservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.cabservice.dao.CabDao;
import com.abc.cabservice.entity.Cab;
import com.abc.cabservice.exception.ResourceNotFoundException;


@Service
public class CabServiceImpl implements CabService {
	@Autowired
	private CabDao cabDao;

	@Override
	public Cab saveCab(Cab newCab) {
		Cab cab = cabDao.save(newCab);
		return cab;
	}

	@Override
	public List<Cab> getAllCabs() {
		List<Cab> cabs = cabDao.findAll();
		return cabs;
	}

	@Override
	public Cab getCabById(int cabId) {

		Optional<Cab> optionalCab = cabDao.findById(cabId);
		if (optionalCab.isEmpty()) {
			throw new ResourceNotFoundException("cab not existing with id: " + cabId);
		}
		Cab cab = optionalCab.get();
		return cab;
	}

	@Override
	public Boolean updateCab(int cabId, Cab cab) {
		Optional<Cab> optionalCab = cabDao.findById(cabId);
		if (optionalCab.isEmpty()) {
			throw new ResourceNotFoundException("cab not existing with id: " + cabId);
		}
		cabDao.save(cab);
		return true;
	}

	@Override
	public Boolean deleteCab(int cabId) {
		Optional<Cab> optionalCab = cabDao.findById(cabId);
		if (optionalCab.isEmpty()) {
			throw new ResourceNotFoundException("cab not existing with id: " + cabId);
		}
		Cab cab = optionalCab.get();
		cabDao.delete(cab);
		return true;
	}

	@Override
	public List<Cab> viewCabsOfType(String carType) {
		List<Cab> cabList = cabDao.findByCabType(carType);

		return cabList;
	}

	@Override
	public int countByCabType(String carType) {
		int count = cabDao.countByCabType(carType);

		return count;
	}

}
