package br.com.eductus.teachermatch;

import static org.easymock.EasyMock.isA;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

/**
 * Test de la clase TeacherMatchApplicationConfig */
@RunWith(EasyMockRunner.class)
public class TeacherMatchApplicationConfig1Test extends EasyMockSupport {
  @TestSubject
  private TeacherMatchApplicationConfig teacherMatchApplicationConfig = new TeacherMatchApplicationConfig();

  @Mock
  private SpringApplication springApplication;

  @Test
  public void testMain1() {
    SpringApplication.run(isA(Class.class), isA(String[].class));
    replayAll();

    String[] string = new String[]{ "z91nw1" };
    TeacherMatchApplicationConfig.main(string);


    verifyAll();
  }
}
