package org.sample.webapp.controller;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sample.controller.IndexController;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SampleService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IndexControllerTest {

    @Mock
    private SampleService sampleService;

    @InjectMocks
    private IndexController indexController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

    }

    
    @Test
    public void testCreateSignupForm() throws Exception {

        when(sampleService.saveFrom(any(SignupForm.class)))
                .thenReturn(null);

        this.mockMvc.perform(post("/create")
                .param("email", "mvcemail@test.com")
                .param("firstName", "mvcfirst")
                .param("lastName", "mvclastname"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(IndexController.PAGE_SHOW));

    }
    
    @Test
    public void testCreateSignupFormInvalidUser() throws Exception {

        when(sampleService.saveFrom(any(SignupForm.class)))
                .thenThrow(new InvalidUserException("For Testing"));

        this.mockMvc.perform(post("/create")
                .param("email", "mvcemail@test.com")
                .param("firstName", "mvcfirst")
                .param("lastName", "mvclastname"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(IndexController.PAGE_INDEX))
                .andExpect(model().attributeExists("page_error"));

    }

    @Test(expected = NestedServletException.class)
    public void testCreateSignupFormRuntimeException() throws Exception {

        when(sampleService.saveFrom(any(SignupForm.class)))
                .thenThrow(new RuntimeException("For Testing"));

        this.mockMvc.perform(post("/create")
                .param("email", "mvcemail@test.com")
                .param("firstName", "mvcfirst")
                .param("lastName", "mvclastname"));
    }

}
