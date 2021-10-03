package sia.taco;

import static org.hamcrest.CoreMatchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import sia.taco.controllers.HomeController;

/*
 * SpringBootTest tells JUnit to bootstrap the test with Spring Boot capabilities.
 * */
@SpringBootTest
class TacoApplicationTests {

	@Autowired
	private HomeController controller;
	/*
	 * this is default test for testing the application context loads correctly or not
	 * */
	@Test
	void contextLoads() {
		/* to check controller is loaded or not */
		assertThat(controller).isNotNull();
	}
	
	/*@Autowired
	private MockMvc mockMvc; 
	
	@Test
	 public void testHomePage() throws Exception {
	 mockMvc.perform(get("/")) 
	 .andExpect(status().isOk()) 
	 .andExpect(view().name("home")) 
	 .andExpect(content().string( 
	 containsString("Welcome to...")));
	 }*/
}
