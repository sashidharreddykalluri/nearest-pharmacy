package com.coding.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.coding.challenge.modal.Pharmacies;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacies, Float> {

    @Query(value = "SELECT p.name, p.id, p.address, p.city, p.state, p.zip,\n"
	    		+ "   (6371 * acos( \n"
	    		+ "	    cos( radians(:userLatitude) ) \n"
	    		+ "	    * cos( radians( p.longitude ) - radians(:userLongitude) ) \n"
	    		+ "	    * cos( radians( p.latitude ) ) \n"
	    		+ "	    + sin( radians(:userLatitude) ) \n"
	    		+ "	    * sin( radians( p.latitude ) )\n"
	    		+ "	   )) as distance"
	    		+ " from pharmacies p"
	    		+ " ORDER BY distance", nativeQuery = true)
    List<Pharmacies> findNearestPharmacyAndSort(@Param("userLatitude") String userLatitude, @Param("userLongitude") String userLongitude);
}