package br.com.eductus.teachermatch.controlllers;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eductus.teachermatch.entities.FormMentorandos;
import br.com.eductus.teachermatch.entities.Mentorandos;
import br.com.eductus.teachermatch.repositories.FormMentorandosRepository;
import br.com.eductus.teachermatch.repositories.MentorandosRepository;
import br.com.eductus.teachermatch.services.EmailService;

@Controller
public class FormMentorandosController {
	
	@Autowired
	MentorandosRepository mentorandosRepository;
	
	@Autowired
	FormMentorandosRepository repository;
	
	@Autowired
	EmailService servicoEmail;
	
	@PostMapping(value = "/cadastrarMentorandos")
	public String cadastrarMentores(
			@RequestParam String email,
			@RequestParam String nome,
			@RequestParam String telefone,
			@RequestParam String nascimento,
			@RequestParam String estado,
			@RequestParam String cidade,
			@RequestParam String questao1,
			@RequestParam String questao1A,
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
			@RequestParam String questao11,
			@RequestParam String questao12,
			@RequestParam String questao13,
			@RequestParam String questao14,
			@RequestParam String questao15,
			@RequestParam String questao16,
			@RequestParam String questao17
			) {

		FormMentorandos form = new FormMentorandos(email, nome, telefone, nascimento, estado, cidade, questao1, questao1A,
				questao2A, questao2B, questao3, questao4, questao5, questao6, questao7, questao8, questao9, questao10,
				questao11, questao12, questao13, questao14, questao15, questao16,questao17);
		repository.save(form);
		
		Date data = new Date();
		String disciplina = questao7 + ", " + questao8;
		PontosMentorandos pontosMentorandos = new PontosMentorandos();
		
		Mentorandos mentorandos = new Mentorandos(null, data, nome, email, telefone, disciplina, questao12, pontosMentorandos.calcularPontuacao(form), questao11);
		mentorandosRepository.save(mentorandos);
		
		try {
			servicoEmail.emailCadastroMentorandos(nome, email.trim(), "Mentoriza - Cadastro Realizado!", mentorandos); 
		} catch (MailException | MessagingException e){
			System.err.println(e.getMessage());
		}
		
		return "redirect:/cadastroOk";
	}
}
