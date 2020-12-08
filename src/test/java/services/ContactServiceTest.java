package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.Contact;
import com.astontech.hr.domain.Element;
import com.astontech.hr.services.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    public void ContactServiceSaveTest() {
        //Create Element Object and assgin a name to contactName
        Contact contact = new Contact("Gene", "Belcher", "Fart Music");

        //save contact, verify it has an ID after the save
        assertNull(contact.getId());
        contactService.saveContact(contact);
        assertNotNull(contact.getId());

        //fetch info from database
        Contact fetchedContact = contactService.getContactById(contact.getId());
        assertNotNull(fetchedContact);
        assertEquals(contact.getId(), fetchedContact.getId());

        //update object and send to database
        fetchedContact.setFirstName("Tina");
        fetchedContact.setCompanyName("Author of Butts");
        contactService.saveContact(fetchedContact);

        //Checked Updated contact on Contact Name
        Contact updatedContact = contactService.getContactById(fetchedContact.getId());
        assertEquals(updatedContact.getCompanyName(), "Author of Butts");
    }
}
