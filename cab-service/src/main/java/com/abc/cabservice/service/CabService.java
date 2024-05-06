package com.abc.cabservice.service;

import java.util.List;


import com.abc.cabservice.entity.Cab;



public interface CabService {
	

	Cab saveCab(Cab newcab);
	List<Cab> getAllCabs();
	Cab getCabById(int cabId);
	Boolean updateCab(int cabId,Cab cab);
	Boolean deleteCab(int cabId);
	
	List<Cab> viewCabsOfType(String carType);
	int countByCabType(String carType);
	

}
