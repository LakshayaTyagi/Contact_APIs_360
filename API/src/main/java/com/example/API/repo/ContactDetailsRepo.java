package com.example.API.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.example.API.model.ContactModel;


@Repository
public interface ContactDetailsRepo extends JpaRepository<ContactModel, Long> {
	
	@Query(value="Select * from Contact_details",nativeQuery=true)
	List<LinkedCaseInsensitiveMap>  findAllContacts();

	@Query(value = "SELECT * FROM Contact_details where first_name=? || email=? || last_name=?;", nativeQuery = true)
	List<LinkedCaseInsensitiveMap> searchContacts(String firstName, String email, String lastName);

	

	
	
}
