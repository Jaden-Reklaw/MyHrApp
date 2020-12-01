package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.User;
import com.astontech.hr.services.UserService;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSaveElementType() {
        //Create User
        User user = new User("Gene", "Belcher", "fartMaster", "fudgeCarWash", "male",  12);

        //save element, verify it has an ID after the save
        assertNull(user.getId());
        userService.saveUser(user);
        assertNotNull(user.getId());

        //fetch info from database
        User fetchedUser = userService.getUserById(user.getId());
        assertNotNull(fetchedUser);
        assertEquals(user.getId(), fetchedUser.getId());

        //update object and send to database
        fetchedUser.setUserName("fartMaster01");
        userService.saveUser(fetchedUser);

        //Checked Updated User by UserName
        User updatedUser = userService.getUserById(fetchedUser.getId());
        assertEquals(updatedUser.getUserName(), "fartMaster01");
    }
}
