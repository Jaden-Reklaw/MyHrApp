package services;
import com.astontech.hr.Application;
import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.Employee;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.EmployeeService;
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
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void employeeServiceSaveTest() {
        //Create Element Object and assgin a name to employeeName
        Employee employee = new Employee("Bob", "Belcher", "Chef");

        //save employee, verify it has an ID after the save
        assertNull(employee.getId());
        employeeService.saveEmployee(employee);
        assertNotNull(employee.getId());

        //fetch info from database
        Employee fetchedEmployee = employeeService.getEmployeeByID(employee.getId());
        assertNotNull(fetchedEmployee);
        assertEquals(employee.getId(), fetchedEmployee.getId());

        //update object and send to database
        fetchedEmployee.setFirstName("Gene");
        employeeService.saveEmployee(fetchedEmployee);

        //Checked Updated employee on Employee Name
        Employee updatedEmployee = employeeService.getEmployeeByID(fetchedEmployee.getId());
        assertEquals(updatedEmployee.getFirstName(), "Gene");
    }
}
