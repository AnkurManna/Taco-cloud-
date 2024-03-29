package sia.taco;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import sia.taco.data.IngredientRepository;

import sia.taco.data.OrderRepository;
import sia.taco.data.TacoRepository;
import sia.taco.data.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  // Note: Most of these mocks are here to avoid autowiring issues. They aren't
  //       actually used in the course of the home page test, so their behavior
  //       isn't important. They just need to exist so autowiring can take place.
  
  @MockBean
  private IngredientRepository ingredientRepository;

  @MockBean
  private TacoRepository designRepository;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private UserRepository userRepository;
  
  @MockBean
  private PasswordEncoder passwordEncoder;

  @Test
  public void testHomePage() throws Exception {
    mockMvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(view().name("home"))
      .andExpect(content().string(
          containsString("Welcome to...")));  
    return;
  }

}
