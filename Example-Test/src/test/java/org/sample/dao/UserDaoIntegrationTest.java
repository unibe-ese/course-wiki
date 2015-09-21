package org.sample.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Team;
import org.sample.model.User;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/springMVC.xml","file:src/main/webapp/WEB-INF/config/springData.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDaoIntegrationTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testTeamReference() {
    	String TEAMNAME = "testTeam"; 
    	
    	Team team = new Team();
        team.setName(TEAMNAME);    	
    	User user = new User();       
        user.setTeam(team);
        user =  userDao.save(user);
        assertEquals(user.getTeam().getName(), TEAMNAME);
    }
    
    @Test
    public void testFindUserByEmail() {
    	String EMAIL =  "bla@bla.bla";
    	
    	User user = new User();
    	user.setEmail(EMAIL);
        user =  userDao.save(user);        
        User findUser = userDao.findByEmail(EMAIL);
        assertEquals(findUser.getEmail(), EMAIL);
    }

}
