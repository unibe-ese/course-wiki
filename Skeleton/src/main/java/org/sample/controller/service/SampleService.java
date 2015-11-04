package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.User;

public interface SampleService {

    public User saveForm(SignupForm signupForm) throws InvalidUserException;

}
