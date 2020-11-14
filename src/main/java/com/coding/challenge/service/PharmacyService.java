package com.coding.challenge.service;

import java.util.List;

import com.coding.challenge.modal.Pharmacies;

public interface PharmacyService {
	/**
	 * Service to handle the user request for nearest pharmacy
	 * 
	 * @param userLatitude - latitude coordinate of the user
	 * @param userLongitude - longitude coordinate of the user
	 * @return
	 */
	List<Pharmacies> findNearestPharmacyToUser(String userLatitude, String userLongitude);
}
