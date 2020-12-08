package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.Address;
import com.astontech.hr.domain.Contact;
import com.astontech.hr.services.AddressService;
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
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void AddressServiceSaveTest() {
        //Create Address Object
        Address address = new Address("123 Main St.", "Blue Ridge", "GA", "50505", "Home");

        //save address, verify it has an ID after the save
        assertNull(address.getId());
        addressService.saveAddress(address);
        assertNotNull(address.getId());

        //fetch info from database
        Address fetchedAddress = addressService.getAddressById(address.getId());
        assertNotNull(fetchedAddress);
        assertEquals(address.getId(), fetchedAddress.getId());

        //update object and send to database
        fetchedAddress.setTypeAddress("Home");
        fetchedAddress.setZipcode("76465");
        addressService.saveAddress(fetchedAddress);

        //Checked Updated address on Address Name
        Address updatedAddress = addressService.getAddressById(fetchedAddress.getId());
        assertEquals(updatedAddress.getTypeAddress(), "Home");
    }
}
