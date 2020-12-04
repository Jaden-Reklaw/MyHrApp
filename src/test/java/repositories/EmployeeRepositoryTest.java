package repositories;
import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.Employee;
import com.astontech.hr.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        //Create Employee Object
        Employee employee = new Employee();
        employee.setFirstName("Jordan");
        employee.setLastName("Walker");
        employee.setBackground("Java Developer");


        //save employee, verify it has an ID after the save
        assertNull(employee.getId());
        employeeRepository.save(employee);
        assertNotNull(employee.getId());

        //fetch info from database
        Employee fetchedEmployee = employeeRepository.findOne(employee.getId());
        assertNotNull(fetchedEmployee);
        assertEquals(employee.getId(), fetchedEmployee.getId());

        //update object and send to database
        fetchedEmployee.setFirstName("Gene");
        employeeRepository.save(fetchedEmployee);

        //Checked Updated employee on Employee Name
        Employee updatedEmployee = employeeRepository.findOne(fetchedEmployee.getId());
        assertEquals(updatedEmployee.getFirstName(), "Gene");

    }
}
