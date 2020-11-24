package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.services.VehicleService;
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
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void vehicleServiceSaveTest() {
        //Create Element Object and assgin a name to elementName
        Vehicle vehicle = new Vehicle(2020, "847-94DF");

        //save element, verify it has an ID after the save
        assertNull(vehicle.getId());
        vehicleService.saveVehicle(vehicle);
        assertNotNull(vehicle.getId());

        //fetch info from database
        Vehicle fetchedVehicle = vehicleService.getVehicleById(vehicle.getId());
        assertNotNull(fetchedVehicle);
        assertEquals(vehicle.getId(), fetchedVehicle.getId());

        //update object and send to database
        fetchedVehicle.setVehiclePlate("777-DDDD");
        vehicleService.saveVehicle(fetchedVehicle);

        //Checked Updated element on Element Name
        Vehicle updatedVehicle = vehicleService.getVehicleById(fetchedVehicle.getId());
        assertEquals(updatedVehicle.getVehiclePlate(), "777-DDDD");
    }
}
