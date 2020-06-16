package br.com.eductus.teachermatch.controlllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
	
	@GetMapping("/formMentores")
	public String formMentores() {
		return "formMentores";
	}
}
