package br.com.eductus.teachermatch.controlllers;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import br.com.eductus.teachermatch.entities.People;
import br.com.eductus.teachermatch.repositories.PeopleRepository;
import br.com.test.utils.EasyMockMockedLoader;

/**
 * Test de la clase PeopleController */
@ContextConfiguration(
    loader = EasyMockMockedLoader.class,
    classes = PeopleController1Test.TestConfig.class
)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(
    classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
)
 public class PeopleController1Test {
  static {
    PowerMockAgent.initializeIfNeeded();
  }

  private MockMvc mockMvc;

  @Autowired
  private PeopleController peopleController;

  @Autowired
  private CrudRepository crudRepository;

  @Autowired
  private Model model;

  @Autowired
  private String string;

  @Rule
  public PowerMockRule rule = new PowerMockRule();

  @Autowired
  private PeopleRepository peopleRepository;

  @Autowired
  private ResponseEntity responseEntity;

  @Autowired
  private ResponseEntity.BodyBuilder bodyBuilder;

  @Autowired
  private EasyMockSupport easyMockSupport;

  @Test
  public void testIndex1() throws Exception {
    easyMockSupport.replayAll();


    mockMvc.perform(get("//")).andDo(print()).andExpect(status().isOk());


    easyMockSupport.verifyAll();
  }

  @Test
  public void testRegister1() throws Exception {
    easyMockSupport.replayAll();


    mockMvc.perform(get("//register")).andDo(print()).andExpect(status().isOk());


    easyMockSupport.verifyAll();
  }

  @Test
  public void testListPeople1() throws Exception {
    crudRepository.findAll();
    model.addAttribute(eq("xbqv0ymnm31n3n7"), isA(Object.class));
    easyMockSupport.replayAll();


    mockMvc.perform(get("//people")).andDo(print()).andExpect(status().isOk());


    easyMockSupport.verifyAll();
  }

  @Test
  public void testSavePeople1() throws Exception {
    string.replaceAll(eq("iftjex"), eq("jo4oh18r35nojg"));
    crudRepository.save(isA(Object.class));
    easyMockSupport.replayAll();


    mockMvc.perform(post("//people/save")).andDo(print()).andExpect(status().isNoContent());


    easyMockSupport.verifyAll();
  }

  @Test
  public void testFindByName1() throws Exception {
    People people = new People();

    List<People> listList = new ArrayList<People>();
    listList.add(people);
    expect(peopleRepository.findByName(eq("13"))).andReturn(listList);

    responseEntity.ok();
    bodyBuilder.body(isA(Object.class));
    easyMockSupport.replayAll();


    mockMvc.perform(get("//people/13")).andDo(print()).andExpect(status().isOk());


    easyMockSupport.verifyAll();

  }

  @Before
  public void initClass() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.peopleController).build();
  }

  @Before
  public void initMocks() {
    expect(peopleController.listPeople(isA(Model.class))).andReturn("25q1so9tz");
  }

  protected static class TestConfig {
    @Bean
    public PeopleController peopleController(EasyMockSupport easyMockSupport) {
      return easyMockSupport.partialMockBuilder(PeopleController.class).addMockedMethod("listPeople").createMock();
    }
  }
}
