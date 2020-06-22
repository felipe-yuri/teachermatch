package br.com.eductus.teachermatch.controlllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping(value = "/formMentorandos")
	public String formMentorandos() {
		return "formMentorandos";
	}
	
	@GetMapping("/formMentores")
	public String formMentores() {
		return "formMentores";
	}

	@GetMapping(value = "/")
	public String index() {
		return "index";
	}

	@GetMapping(value = "/register")
	public String register() {
		return "register";
	}
}
