package br.com.eductus.teachermatch.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eductus.teachermatch.entities.FormMentores;
import br.com.eductus.teachermatch.repositories.FormMentoresRepository;

@Controller
public class FormMentoresController {
	
	@Autowired
	FormMentoresRepository repository;
	
	@PostMapping(value = "/cadastrarMentores")
	public String cadastrarMentores(
			@RequestParam String email,
			@RequestParam String nome,
			@RequestParam String telefone,
			@RequestParam String nascimento,
			@RequestParam String estado,
			@RequestParam String cidade,
			@RequestParam String questao1,
			@RequestParam String questao2,
			@RequestParam String questao3A,
			@RequestParam String questao3B,
			@RequestParam String questao4,
			@RequestParam String questao5,
			@RequestParam String questao6,
			@RequestParam String questao7,
			@RequestParam String questao8A, 
			@RequestParam String questao8B,
			@RequestParam String questao8C,
			@RequestParam String questao8D,
			@RequestParam String questao8E,
			@RequestParam String questao8F,
			@RequestParam String questao8G,
			@RequestParam String questao9,
			@RequestParam String questao10A, 
			@RequestParam String questao10B, 
			@RequestParam String questao11, 
			@RequestParam String questao12, 
			@RequestParam String questao13 
			) {
		FormMentores form = new FormMentores(null, email, nome, telefone, nascimento, estado, cidade, questao1,
				questao2, questao3A, questao3B, questao4, questao5, questao6, questao7, questao8A, questao8B, questao8C, questao8D,
				questao8E, questao8F, questao8G, questao9, questao10A, questao10B, questao11, questao12, questao13);
		repository.save(form);
		
		return "redirect:/cadastroOk";
	}
}
