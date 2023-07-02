package com.example.API.service;

import java.util.Map;

import com.example.API.model.ContactModel;

public interface ContactDetailsService {
	
	 public Map saveContactDetails(ContactModel depart);
	 
	 public Map deleteContactDetails(long id);
	 
	 public Map getAllContactDetails();
	 
	 public Map updateContactDetails(ContactModel contactModel);

	 public Map searchContacts(ContactModel contactModel);
	 

}
