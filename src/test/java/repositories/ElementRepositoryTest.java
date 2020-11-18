package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ElementRepositoryTest {
    @Autowired
    private ElementRepository elementRepository;

    @Test
    public void testSaveElement() {
        //Create Element Object and assgin a name to elementName
        Element element = new Element("Phone");

        //save element, verify it has an ID after the save
        assertNull(element.getId());
        elementRepository.save(element);
        assertNotNull(element.getId());

        //fetch info from database
        Element fetchedElement = elementRepository.findOne(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update object and send to database
        fetchedElement.setElementName("Email");
        elementRepository.save(fetchedElement);

        //Checked Updated element on Element Name
        Element updatedElement = elementRepository.findOne(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");

    }

    @Test
    public void testSaveElementList() {
        List<Element> elementList = new ArrayList<>();

        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        //Fetch List from Database
        Iterable<Element> fetchedElementList = elementRepository.findAll();

        //How to get size of iterable
        int count = ((Collection<?>) fetchedElementList).size();

        //Check if count is 5 since you put 4 Elements in List then add plus the one from the previous test
        //assertEquals(5, count);
    }

    @Test
    public void testFindByName() {
        //Create element and save to database
        Element element = new Element("Fire");
        elementRepository.save(element);

        //Get the element by name from the repository
        Element foundByNameElement = elementRepository.findByElementName("Fire");

        //Check against Id to see if its the same element
        assertEquals(element.getId(), foundByNameElement.getId());
    }

    @Test
    public void testFindAllByName() {
        //Create element and save to database
        Element element1 = new Element("Ice");
        elementRepository.save(element1);
        Element element2 = new Element("Ice");
        elementRepository.save(element2);

        //Get all elements that have the same name
        List<Element> foundAllByNameElement = elementRepository.findAllByElementName("Ice");

        //Check the size which should be two since two ices where inserted
        assertEquals(2, foundAllByNameElement.size());
    }
}
