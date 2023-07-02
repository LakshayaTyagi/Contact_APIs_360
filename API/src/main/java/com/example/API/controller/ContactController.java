package com.example.API.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.model.ContactModel;
import com.example.API.repo.ContactDetailsRepo;
import com.example.API.service.ContactDetailsService;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactDetailsService contactService;

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

	@PostMapping("/addContact")
	public Map addContactDetails(@RequestBody ContactModel contactdetails) {
		Map response = new HashMap();
		try {
			response = contactService.saveContactDetails(contactdetails);
		} catch (Exception e) {
			logger.error("Exception occured while adding contacts..");
			response.put("status", "exception");
			response.put("msg", e.getMessage());

		}

		return response;

	}

	@PostMapping("/deleteContact")
	public Map deleteContactDetails(@RequestBody long contactId) {
		Map response = new HashMap();
		try {
			response = contactService.deleteContactDetails(contactId);
		} catch (Exception e) {
			logger.error("Exception occured while deleting contacts..");
			response.put("status", "exception");
			response.put("msg", e.getMessage());

		}

		return response;

	}

	@PostMapping("/getAllContacts")
	public Map getAllContacts() {

		Map response = new HashMap();
		try {
			response = contactService.getAllContactDetails();
		} catch (Exception e) {
			logger.error("Exception occured while getting contacts..");
			response.put("status", "exception");
			response.put("msg", e.getMessage());
		}

		return response;

	}

	@PostMapping("/updateContact")
	public Map updateContact(ContactModel contactModel) {

		Map response = new HashMap();
		try {
			response = contactService.updateContactDetails(contactModel);
		} catch (Exception e) {
			logger.error("Exception occured while updating contacts..");
			response.put("status", "exception");
			response.put("msg", e.getMessage());
		}

		return response;

	}

	@PostMapping("/searchContact")
	public Map searchContacts(ContactModel contactModel) {
		
		Map response = new HashMap();
		try {
			response = contactService.searchContacts(contactModel);
		} catch (Exception e) {
			logger.error("Exception occured while updating contacts..");
			response.put("status", "exception");
			response.put("msg", e.getMessage());
		}

		return response;

	}

}
