

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml" })

@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ControllerTest {

	@Autowired private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetSignupForm() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(forwardedUrl("/pages/index.jsp"));

	}

	@Test
	public void testCreateSignupFormErrors() throws Exception {

		this.mockMvc
				.perform(
						post("/create").param("email", "<error>")
								.param("firstName", "<error>")
								.param("lastName", "<error>"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/pages/index.jsp"))
				.andExpect(
						model().attributeHasFieldErrors("signupForm", "email"));

	}

	@Test
	public void testCreateSignupFormInvalidUser() throws Exception {

		this.mockMvc
				.perform(
						post("/create").param("email", "mvcemail@test.com")
								.param("firstName", "ese")
								.param("lastName", "mvclastname"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/pages/index.jsp"))
				.andExpect(model().attributeExists("page_error"));

	}

	@Test
	public void testCreateSignupForm() throws Exception {
		this.mockMvc
				.perform(
						post("/create").param("email", "mvcemail@test.com")
								.param("firstName", "mvcfirstname")
								.param("lastName", "mvclastname"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/pages/show.jsp"));

	}

	@Test
	public void testSecurityError() throws Exception {
		this.mockMvc.perform(get("/security-error"))
				.andExpect(status().isFound()).andExpect(redirectedUrl("/"))
				.andExpect(flash().attributeExists("page_error"));
	}
}
