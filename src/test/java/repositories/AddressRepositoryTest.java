package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Address;
import com.astontech.hr.domain.Contact;
import com.astontech.hr.repositories.AddressRepository;
import com.astontech.hr.repositories.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testSaveContact() {
        //Create Contact Object
        Address newAddress = new Address("123 Main St.", "Blue Ridge", "GA", "50505", "Home");

        //Save contact, verify it has an ID after saved
        assertNull(newAddress.getId());
        addressRepository.save(newAddress);
        assertNotNull(newAddress.getId());

        //fetch new contact from the database
        Address fetchedAddress = addressRepository.findOne(newAddress.getId());
        assertNotNull(fetchedAddress);
        assertEquals(newAddress.getId(), fetchedAddress.getId());

        //update new contact and send back to database
        fetchedAddress.setTypeAddress("Work");
        fetchedAddress.setZipcode("50005");
        addressRepository.save(fetchedAddress);

        //Checked updated contact on firstname
        Address updatedAddresss = addressRepository.findOne(fetchedAddress.getId());
        assertEquals(updatedAddresss.getTypeAddress(), "Work");
    }
}
