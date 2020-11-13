package com.coding.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.coding.challenge.modal.Pharmacies;
import com.coding.challenge.service.PharmacyService;

@RestController
public class NearestPharmacyController {

	@Autowired
	PharmacyService service;
	
	@GetMapping("/pharmacy-near-me")
	public ResponseEntity<Pharmacies> getInfo(
			@RequestParam(value = "latitude", required = true) String userLatitude, 
			@RequestParam(value = "longitude", required = true) String userLongitude) {
		//TO DO: Add regex validation for latitude and longitude coordinates
		
		// validate query params fields
		if(userLatitude == "" || userLongitude == "") {
			throw new IllegalArgumentException("{\"error\":\"At least one parameter is invalid\"}");
		}
		
		//call pharmacy service to return nearest pharmacy
		List<Pharmacies> resultingNearestPharmacy = service.findNearestPharmacyToUser(userLatitude, userLongitude);
		return new ResponseEntity<>(resultingNearestPharmacy.get(0), HttpStatus.OK);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final String exceptionHandlerIllegalArgumentException(final IllegalArgumentException e) {
	    return '"' + e.getMessage() + '"';
	}
}
