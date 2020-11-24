package repositories;
import com.astontech.hr.configuration.RepositoryConfiguration;

import com.astontech.hr.domain.VehicleModel;

import com.astontech.hr.repositories.VehicleModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class VehicleModelRepositoryTest {
    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Test
    public void testSaveElementType() {
        //Create VehicleModel Object
        VehicleModel vehicleModel = new VehicleModel("Ford");

        //save element, verify it has an ID after the save
        assertNull(vehicleModel.getId());
        vehicleModelRepository.save(vehicleModel);
        assertNotNull(vehicleModel.getId());

        //fetch info from database
        VehicleModel fetchedVehicleModel = vehicleModelRepository.findOne(vehicleModel.getId());
        assertNotNull(fetchedVehicleModel);
        assertEquals(vehicleModel.getId(), fetchedVehicleModel.getId());

        //update object and send to database
        fetchedVehicleModel.setVehicleModelName("Chevrolet");
        vehicleModelRepository.save(fetchedVehicleModel);

        //Checked Updated element on Element Name
        VehicleModel updatedVehicleModel = vehicleModelRepository.findOne(fetchedVehicleModel.getId());
        assertEquals(updatedVehicleModel.getVehicleModelName(), "Chevrolet");
    }

}
