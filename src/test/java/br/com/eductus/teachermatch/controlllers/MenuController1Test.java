package br.com.eductus.teachermatch.controlllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.test.utils.EasyMockMockedLoader;

/**
 * Test de la clase MenuController */
@ContextConfiguration(
    loader = EasyMockMockedLoader.class,
    classes = MenuController1Test.TestConfig.class
)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(
    classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
)
public class MenuController1Test {
  private MockMvc mockMvc;

  @Autowired
  private MenuController menuController;


  @Test
  public void testFormMentores1() throws Exception {


    mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());


  }

  @Before
  public void initClass() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.menuController).build();
  }

  protected static class TestConfig {
    @Bean
    public MenuController menuController() {
      return new MenuController();
    }
  }
}
