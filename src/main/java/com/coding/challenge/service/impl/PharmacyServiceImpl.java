package com.coding.challenge.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coding.challenge.modal.Pharmacies;
import com.coding.challenge.repository.PharmacyRepository;
import com.coding.challenge.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService{

	@Autowired
    private PharmacyRepository pharmacyRepository;
	
	@Override
	public List<Pharmacies> findNearestPharmacyToUser(String userLatitude, String userLongitude) {
		List<Pharmacies> list = pharmacyRepository.findNearestPharmacyAndSort(userLatitude, userLongitude);
		return list;
	}
}
