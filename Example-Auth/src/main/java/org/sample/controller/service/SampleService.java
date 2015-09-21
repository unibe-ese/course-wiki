package org.sample.controller.service;

import org.sample.controller.pojos.SignupForm;
import org.sample.exceptions.InvalidUserException;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;

}
