package comexample.API.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.example.API.model.ContactModel;
import com.example.API.repo.ContactDetailsRepo;
import com.example.API.service.ContactDetailsService;

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {

	@Autowired
	private ContactDetailsRepo contactRepo;

	private static final Logger logger = LoggerFactory.getLogger(ContactDetailsServiceImpl.class);

	@Override
	public Map saveContactDetails(ContactModel contact) {
		Map response = new HashMap();
		try {

			contactRepo.save(contact);
			response.put(HttpStatus.OK, "Contact added successfully");
			response.put("status", "success");
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("status", "exception");
			response.put("msg", e.getMessage());
		}
		return response;
	}

	@Override
	public Map deleteContactDetails(long id) {

		Map response = new HashMap();
		boolean check=false;
		try {
			List<LinkedCaseInsensitiveMap>  list = contactRepo.findAllContacts();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).get("contact_id").equals(id)) {
					check=true;
					contactRepo.deleteById(id);
					response.put("status", HttpStatus.OK);
					response.put("msg", "Contact Deleted successfully..");
					break;
				}
				
			}
			if(check==false) {
				throw new RuntimeException("Id doesn't exist..");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("status", "exception");
			response.put("msg", e.getMessage());
		}

		return response;

	}

	@Override
	public Map getAllContactDetails() {
		Map response = new HashMap();
		try {
			List<LinkedCaseInsensitiveMap> list = contactRepo.findAllContacts();
			response.put("status", HttpStatus.OK);
			response.put("ContactList", list);
			

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("status", "exception");
			response.put("msg", e.getMessage());
		}

		return response;
	}

	@Override
	public Map updateContactDetails(ContactModel contactModel) {
		Map response = new HashMap();
		try {
			ContactModel contact = contactRepo.findById(contactModel.getContactId()).get();
			contact.setEmail(contactModel.getEmail());
			contact.setFirstName(contactModel.getLastName());
			contact.setLastName(contactModel.getFirstName());
			contact.setPhoneNumber(contactModel.getPhoneNumber());
			response.put("status", HttpStatus.OK);
			response.put("msg", "contact details updates successfully..");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			response.put("status", "exception");
			response.put("msg", e.getMessage());
		}
		return response;
	}
	
	

}
