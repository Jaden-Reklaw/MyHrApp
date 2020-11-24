package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.services.VehicleMakeService;
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
public class VehicleMakeServiceTest {
    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Test
    public void vehicleServiceSaveTest() {
        //Create Element Object and assgin a name to elementName
        VehicleMake vehicleMake = new VehicleMake("Ford");

        //save element, verify it has an ID after the save
        assertNull(vehicleMake.getId());
        vehicleMakeService.saveVehicleMake(vehicleMake);
        assertNotNull(vehicleMake.getId());

        //fetch info from database
        VehicleMake fetchedVehicleMake = vehicleMakeService.getVehicleMakeById(vehicleMake.getId());
        assertNotNull(fetchedVehicleMake);
        assertEquals(vehicleMake.getId(), fetchedVehicleMake.getId());

        //update object and send to database
        fetchedVehicleMake.setVehicleMakeName("Mazda");
        vehicleMakeService.saveVehicleMake(fetchedVehicleMake);

        //Checked Updated element on Element Name
        VehicleMake updatedVehicleModel = vehicleMakeService.getVehicleMakeById(fetchedVehicleMake.getId());
        assertEquals(updatedVehicleModel.getVehicleMakeName(), "Mazda");
    }
}
