package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.services.VehicleModelService;
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
public class VehicleModelServiceTest {

    @Autowired
    private VehicleModelService vehicleModelService;

    @Test
    public void vehicleServiceSaveTest() {
        //Create Element Object and assgin a name to elementName
        VehicleModel vehicleModel = new VehicleModel("CX-5");

        //save element, verify it has an ID after the save
        assertNull(vehicleModel.getId());
        vehicleModelService.saveVehicleModel(vehicleModel);
        assertNotNull(vehicleModel.getId());

        //fetch info from database
        VehicleModel fetchedVehicleModel = vehicleModelService.getVehicleModelById(vehicleModel.getId());
        assertNotNull(fetchedVehicleModel);
        assertEquals(vehicleModel.getId(), fetchedVehicleModel.getId());

        //update object and send to database
        fetchedVehicleModel.setVehicleModelName("CX-7");
        vehicleModelService.saveVehicleModel(fetchedVehicleModel);

        //Checked Updated element on Element Name
        VehicleModel updatedVehicleModel = vehicleModelService.getVehicleModelById(fetchedVehicleModel.getId());
        assertEquals(updatedVehicleModel.getVehicleModelName(), "CX-7");
    }
}
