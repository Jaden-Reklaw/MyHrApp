package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.repositories.VehicleMakeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class VehicleMakeRepositoryTest {
    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @Test
    public void testSaveVehicleMake() {
        //Create VehicleMake Object
        VehicleMake vehicleMake = new VehicleMake("Ford");

        //save element, verify it has an ID after the save
        assertNull(vehicleMake.getId());
        vehicleMakeRepository.save(vehicleMake);
        assertNotNull(vehicleMake.getId());

        //fetch info from database
        VehicleMake fetchedVehicleModel = vehicleMakeRepository.findOne(vehicleMake.getId());
        assertNotNull(fetchedVehicleModel);
        assertEquals(vehicleMake.getId(), fetchedVehicleModel.getId());

        //update object and send to database
        fetchedVehicleModel.setVehicleMakeName("Chevrolet");
        vehicleMakeRepository.save(fetchedVehicleModel);

        //Checked Updated element on Element Name
        VehicleMake updatedVehicleModel = vehicleMakeRepository.findOne(fetchedVehicleModel.getId());
        assertEquals(updatedVehicleModel.getVehicleMakeName(), "Chevrolet");
    }
}
