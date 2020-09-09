package br.com.eductus.teachermatch.controlllers;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eductus.teachermatch.entities.FormMentores;
import br.com.eductus.teachermatch.entities.Mentores;
import br.com.eductus.teachermatch.repositories.FormMentoresRepository;
import br.com.eductus.teachermatch.repositories.MentoresRepository;
import br.com.eductus.teachermatch.services.EmailService;

@Controller
public class FormMentoresController {
	
	@Autowired
	MentoresRepository MentoresRepository;
	
	@Autowired
	FormMentoresRepository formMentoresRepository;
	
	@Autowired
	EmailService servicoEmail;
	
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
			@RequestParam String questao3C,
			@RequestParam String questao4,
			@RequestParam String questao5,
			@RequestParam String questao6,
			@RequestParam String questao7,
			@RequestParam String questao8A, 
			@RequestParam String questao8B,
			@RequestParam String questao8C,
			@RequestParam String questao8D,
			@RequestParam String questao8E,
			@RequestParam String questao9,
			@RequestParam String questao10A, 
			@RequestParam String questao10B, 
			@RequestParam String questao11, 
			@RequestParam String questao12, 
			@RequestParam String questao13,
			@RequestParam String questao14,
			@RequestParam String questao15,
			@RequestParam String questao16
			) {
		FormMentores form = new FormMentores(email, nome, telefone, nascimento, estado, cidade, questao1,
				questao2, questao3A, questao3B, questao3C, questao4, questao5, questao6, questao7, questao8A, questao8B, questao8C, questao8D,
				questao8E, questao9, questao10A, questao10B, questao11, questao12, questao13, questao14, questao15, questao16);
		formMentoresRepository.save(form);
		
		Date data = new Date();
		String disciplina = questao6 + ", " + questao7;
		PontosMentores pontosMentores = new PontosMentores();
		
		Mentores mentores = new Mentores(null, data, nome, email, telefone, disciplina, pontosMentores.calcularPontos(form), questao2, questao12);
		MentoresRepository.save(mentores);
		
		try {
			servicoEmail.emailCadastroMentores(nome, email.trim(), "Mentoriza - Cadastro Realizado!", mentores); 
		} catch (MailException | MessagingException e){
			System.err.println(e.getMessage());
		}
		
		return "redirect:/cadastroOk";
	}
}
