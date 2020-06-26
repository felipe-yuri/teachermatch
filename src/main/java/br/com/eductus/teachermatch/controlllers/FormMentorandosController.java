package br.com.eductus.teachermatch.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eductus.teachermatch.entities.FormMentorandos;
import br.com.eductus.teachermatch.repositories.FormMentorandosRepository;

@Controller
public class FormMentorandosController {
	
	@Autowired
	FormMentorandosRepository repository;
	
	@PostMapping(value = "/cadastrarMentorandos")
	public String cadastrarMentores(
			@RequestParam String email,
			@RequestParam String nome,
			@RequestParam String telefone,
			@RequestParam String nascimento,
			@RequestParam String estado,
			@RequestParam String cidade,
			@RequestParam String questao1,
			@RequestParam String questao2A,
			@RequestParam String questao2B,
			@RequestParam String questao3,
			@RequestParam String questao4,
			@RequestParam String questao5,
			@RequestParam String questao6,
			@RequestParam String questao7,
			@RequestParam String questao8, 
			@RequestParam String questao9,
			@RequestParam String questao10, 
			@RequestParam String questao11
			) {
		
		FormMentorandos form = new FormMentorandos(null, email, nome, telefone, nascimento, estado, cidade, questao1,
				questao2A, questao2B, questao3, questao4, questao5, questao6, questao7, questao8, questao9, questao10,
				questao11);
		repository.save(form);
		
		return "/cadastroOk";
	}
}
