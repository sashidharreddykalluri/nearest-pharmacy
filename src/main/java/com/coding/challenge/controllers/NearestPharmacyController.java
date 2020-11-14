package com.coding.challenge.controllers;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;

import com.coding.challenge.modal.Pharmacies;
import com.coding.challenge.service.PharmacyService;

@RestController
@Validated
public class NearestPharmacyController {

	@Autowired
	PharmacyService service;
	
	/**
	 * Function to handle the get nearest pharmacy endpoint {ex:../pharmacies/pharmacy-near-me?latitude={userLatitude}&longitude={userLongitude}}
	 * 
	 * @param userLatitude - latitude coordinate of the user
	 * @param userLongitude - longitude coordinate of the user
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/pharmacy-near-me")
	public ResponseEntity<Pharmacies> getNearestPharmacy(
			@RequestParam(value = "latitude", required = true) @NotEmpty @Min(-90) @Max(90) String userLatitude, 
			@RequestParam(value = "longitude", required = true)@NotEmpty @Min(-180) @Max(180) String userLongitude) {
		
		// call pharmacy service to return nearest pharmacy
		List<Pharmacies> resultingNearestPharmacy = service.findNearestPharmacyToUser(userLatitude, userLongitude);
		return new ResponseEntity<>(resultingNearestPharmacy.get(0), HttpStatus.OK);
	}
	
	/**
	 * returns exception when either of the coordinates are not within the specified range or are invalid
	 * 
	 * @param e
	 * @return {@link ConstraintViolationException}
	 */
	@ExceptionHandler({ConstraintViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final String exceptionHandlerIllegalArgumentException(final ConstraintViolationException e) {
	    return "Bad request due to either of the following:: " + e.getMessage();
	}
}
