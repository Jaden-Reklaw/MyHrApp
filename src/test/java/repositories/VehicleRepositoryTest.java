package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.repositories.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class VehicleRepositoryTest {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testSaveVehicle() {
        //Create Element Object and assgin a name to elementName
        Vehicle vehicle = new Vehicle(2008, "zzzzzzz");

        //save element, verify it has an ID after the save
        assertNull(vehicle.getId());
        vehicleRepository.save(vehicle);
        assertNotNull(vehicle.getId());

        //fetch info from database
        Vehicle fetchedVehicle = vehicleRepository.findOne(vehicle.getId());
        assertNotNull(fetchedVehicle);
        assertEquals(vehicle.getId(), fetchedVehicle.getId());

        //update object and send to database
        fetchedVehicle.setVehicleYear(2009);
        vehicleRepository.save(fetchedVehicle);

        //Checked Updated element on Element Name
        Vehicle updatedVehicle = vehicleRepository.findOne(fetchedVehicle.getId());
        assertEquals(updatedVehicle.getVehicleYear(), 2009);
    }

    @Test
    public void testSaveVehicleList() {
        List<Vehicle> vehicleList = new ArrayList<>();

        vehicleList.add(new Vehicle(2001, "aaaaaa"));
        vehicleList.add(new Vehicle(2002, "bbbbbb"));
        vehicleList.add(new Vehicle(2003, "cccccc"));
        vehicleList.add(new Vehicle(2004, "dddddd"));

        vehicleRepository.save(vehicleList);

        //Fetch List from Database
        Iterable<Vehicle> fetchedVehicleList = vehicleRepository.findAll();

        for(Vehicle vehicle: fetchedVehicleList) {
            System.out.println("Year: " + vehicle.getVehicleYear() + "\n" +
                    "Plate: " + vehicle.getVehiclePlate());
        }
        //How to get size of iterable
        int count = ((Collection<?>) fetchedVehicleList).size();

        //Check if count is 6
        assertEquals(6, count);
    }

    @Test
    public void testFindByYear() {
        //Create element and save to database
        Vehicle vehicle = new Vehicle(2020, "gggggg");
        vehicleRepository.save(vehicle);

        //Get the element by name from the repository
        Vehicle newVehicle = vehicleRepository.findByVehicleYearAndVehiclePlate(2020, "gggggg");

        //Check against Id to see if its the same element
        assertEquals(vehicle.getId(), newVehicle.getId());
    }
}
