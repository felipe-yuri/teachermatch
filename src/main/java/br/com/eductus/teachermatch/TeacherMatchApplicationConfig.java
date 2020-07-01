package br.com.eductus.teachermatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TeacherMatchApplicationConfig extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(TeacherMatchApplicationConfig.class, args);
	}
}
