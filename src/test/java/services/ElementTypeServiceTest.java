package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.services.ElementTypeService;
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
public class ElementTypeServiceTest {

    @Autowired
    private ElementTypeService elementTypeService;

    @Test
    public void testSaveElementType() {
        //Create ElementType Object and assgin a ElementType to elementType
        ElementType elementType = new ElementType("Work");

        //save element, verify it has an ID after the save
        assertNull(elementType.getId());
        elementTypeService.saveElementType(elementType);
        assertNotNull(elementType.getId());

        //fetch info from database
        ElementType fetchedElementType = elementTypeService.getElementTypeById(elementType.getId());
        assertNotNull(fetchedElementType);
        assertEquals(elementType.getId(), fetchedElementType.getId());

        //update object and send to database
        fetchedElementType.setElementType("Email");
        elementTypeService.saveElementType(fetchedElementType);

        //Checked Updated element on Element Name
        ElementType updatedElementType = elementTypeService.getElementTypeById(fetchedElementType.getId());
        assertEquals(updatedElementType.getElementType(), "email");
    }
}
