package com.astontech.hr.rest;

import com.astontech.hr.domain.Contact;
import com.astontech.hr.services.ContactService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact/")
public class ContactRest {

    private Logger log = Logger.getLogger(EmployeeRest.class);

    @Autowired
    private ContactService contactService;

    //Return all contacts
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Contact> getAllContacts() {
        return contactService.listAllContacts();
    }

    //Returns contact based on the ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Contact getContactById(@PathVariable int id) {
        return contactService.getContactById(id);
    }

    //Add new contact to the database
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Contact saveNewContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }

    //Delete a contact
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteContactById(@PathVariable int id) {
        boolean result = false;
        try {
            contactService.deleteContact(id);
            result = true;
        } catch (Exception ex) {
            log.info(ex);
        }
        return result;
    }
}
