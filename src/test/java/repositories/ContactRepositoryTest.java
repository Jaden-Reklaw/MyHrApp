package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Contact;
import com.astontech.hr.repositories.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testSaveContact() {
        //Create Contact Object
        Contact newContact = new Contact("Bob", "Belcher", "Bob's Burgers");

        //Save contact, verify it has an ID after saved
        assertNull(newContact.getId());
        contactRepository.save(newContact);
        assertNotNull(newContact.getId());

        //fetch new contact from the database
        Contact fetchedContact = contactRepository.findOne(newContact.getId());
        assertNotNull(fetchedContact);
        assertEquals(newContact.getId(), fetchedContact.getId());

        //update new contact and send back to database
        fetchedContact.setFirstName("Linda");
        fetchedContact.setCompanyName("Linda's Bakery");
        contactRepository.save(fetchedContact);

        //Checked updated contact on firstname
        Contact updatedContact = contactRepository.findOne(fetchedContact.getId());
        assertEquals(updatedContact.getFirstName(), "Linda");
    }
}
