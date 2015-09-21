package org.sample.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SampleService;
import org.sample.controller.service.SampleServiceImpl;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.TeamDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/test-context.xml"})
public class SampleServiceTest {

	@Autowired
	@Qualifier("userDaoMock")
    private UserDao userDao;
	
	@Autowired
    private SampleService sampleService;

    @Before
    public void doSetup() {
        userDao = mock(UserDao.class);
    }

    @Test
    public void testSaveForm() {

        SignupForm signupForm = new SignupForm();
        signupForm.setLastName("formLast");
        signupForm.setFirstName("formFirst");
        signupForm.setEmail("form@test.com");

        when(userDao.save(any(User.class)))
                .thenAnswer(new Answer<User>() {
                    public User answer(InvocationOnMock invocation) throws Throwable {
                        User user = (User) invocation.getArguments()[0];
                        user.setId(1L);
                        return user;
                    }
                });

        assertNull(signupForm.getId());

        signupForm = sampleService.saveFrom(signupForm);

        assertNotNull(signupForm.getId());
        assertTrue(signupForm.getId() > 0);
    }

    @Test(expected = InvalidUserException.class)
    public void testInvalidUserException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setLastName("formLast");
        signupForm.setFirstName("ESE");
        signupForm.setEmail("form@test.com");

        sampleService.saveFrom(signupForm);
    }


}