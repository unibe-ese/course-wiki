package org.sample.controller;

import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SampleService;
import org.sample.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class loginController {

    @Autowired
    SampleService sampleService;


    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value="error", required=false) boolean error,      ModelMap model) {
    
     // Add an error message to the model if login is unsuccessful
     // The 'error' parameter is set to true based on the when the authentication has failed. 
     // We declared this under the authentication-failure-url attribute inside the spring-security.xml

     if (error == true) {
      // Assign an error message
      model.put("error", "You have entered an invalid username or password!");
     } else {
      model.put("error", "");
     }
      
     // This will resolve to /WEB-INF/jsp/loginpage.jsp
     return "login";
    }
     
    /**
     * Handles and retrieves the denied JSP page. This is shown whenever a regular user
     * tries to access an admin only page.
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
     public String getDeniedPage() {
      
     // This will resolve to /WEB-INF/jsp/deniedpage.jsp
     return "deniedpage";
    }

  
}


