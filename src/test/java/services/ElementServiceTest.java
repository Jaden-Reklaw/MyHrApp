package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.Element;
import com.astontech.hr.services.ElementService;
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
public class ElementServiceTest {

    //In service autowire the service not the repository
    @Autowired
    private ElementService elementService;

    @Test
    public void elementServiceSaveTest() {
        //Create Element Object and assgin a name to elementName
        Element element = new Element("Phone");

        //save element, verify it has an ID after the save
        assertNull(element.getId());
        elementService.saveElement(element);
        assertNotNull(element.getId());

        //fetch info from database
        Element fetchedElement = elementService.getElementById(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update object and send to database
        fetchedElement.setElementName("Email");
        elementService.saveElement(fetchedElement);

        //Checked Updated element on Element Name
        Element updatedElement = elementService.getElementById(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");
    }
}
