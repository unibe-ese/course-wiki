package org.sample.controller;

import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SampleService;
import org.sample.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class adminController {

    @Autowired
    SampleService sampleService;


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("admin");
    	
    	
        return model;
    }

  
}


