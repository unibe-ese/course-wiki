package test;


import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SampleService;
import org.sample.model.User;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class ServiceTest {

		
	@Autowired private SampleService sampleService;
	@Autowired 	private UserDao userDao;

	@BeforeClass
    public static void oneTimeSetUp() {	
    }
	@Before
    public void setUp() {	
    }
    

    @Test
    public void testSaveForm() {
    	// GIVEN
    	when(userDao.save(any(User.class))).then(returnsFirstArg());

    	// WHEN	
        SignupForm signupForm = new SignupForm();
        signupForm.setLastName("formLast");
        signupForm.setFirstName("formFirst");
        signupForm.setEmail("form@test.com");

        User user = sampleService.saveForm(signupForm);
        
        // THEN
        assertEquals("formLast", user.getLastName());
    }

    
    @Test(expected = InvalidUserException.class)
    public void testInvalidUserException() {
    	// WHEN
        SignupForm signupForm = new SignupForm();
        signupForm.setLastName("formLast");
        signupForm.setFirstName("ESE");
        signupForm.setEmail("form@test.com");

        sampleService.saveForm(signupForm);  // throws ex
    }

}
