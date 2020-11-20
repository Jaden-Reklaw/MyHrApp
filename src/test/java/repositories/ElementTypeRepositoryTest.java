package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.repositories.ElementRepository;
import com.astontech.hr.repositories.ElementTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ElementTypeRepositoryTest {
    @Autowired
    private ElementTypeRepository elementTypeRepository;

    @Test
    public void testSaveElementType() {
        //Create ElementType Object and assgin a ElementType to elementType
        ElementType elementType = new ElementType("Work");

        //save element, verify it has an ID after the save
        assertNull(elementType.getId());
        elementTypeRepository.save(elementType);
        assertNotNull(elementType.getId());

        //fetch info from database
        ElementType fetchedElementType = elementTypeRepository.findOne(elementType.getId());
        assertNotNull(fetchedElementType);
        assertEquals(elementType.getId(), fetchedElementType.getId());

        //update object and send to database
        fetchedElementType.setElementTypeName("Email");
        elementTypeRepository.save(fetchedElementType);

        //Checked Updated element on Element Name
        ElementType updatedElementType = elementTypeRepository.findOne(fetchedElementType.getId());
        assertEquals(updatedElementType.getElementTypeName(), "Email");
    }
}
