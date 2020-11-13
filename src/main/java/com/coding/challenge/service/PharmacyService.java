package com.coding.challenge.service;

import java.util.List;

import com.coding.challenge.modal.Pharmacies;

public interface PharmacyService {
	List<Pharmacies> findNearestPharmacyToUser(String latitude, String longitude);
}
