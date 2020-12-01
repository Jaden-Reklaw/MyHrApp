package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.User;
import com.astontech.hr.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveElementType() {
        //Create ElementType Object and assgin a ElementType to elementType
        User user = new User("Gene", "Belcher", "burgerboss67", "test123@", "Male", 13);

        //save element, verify it has an ID after the save
        assertNull(user.getId());
        userRepository.save(user);
        assertNotNull(user.getId());

        //fetch info from database
        User fetchedUser = userRepository.findOne(user.getId());
        assertNotNull(fetchedUser);
        assertEquals(user.getId(), fetchedUser.getId());

        //update object and send to database
        fetchedUser.setFirstName("Bob");
        fetchedUser.setAge(46);
        userRepository.save(fetchedUser);

        //Checked Updated element on Element Name
        User updatedUser = userRepository.findOne(fetchedUser.getId());
        assertEquals(updatedUser.getFirstName(), "Bob");
        assertEquals(updatedUser.getPassword(), "test123@");
        assertEquals(updatedUser.getAge(), 46);
    }
}
