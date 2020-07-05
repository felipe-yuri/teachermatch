package br.com.eductus.teachermatch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender enviarEmail;
	
	@Autowired
	private SimpleMailMessage mensagemEmail;
	
	@Value("${mail.username}")
	private String emailMentoriza;
	
	public void emailCadastroOk(String nome, String para, String assunto) {
		mensagemEmail.setTo(para);
		mensagemEmail.setFrom(emailMentoriza);
		mensagemEmail.setSubject(assunto);
		mensagemEmail.setText("Olá! " + nome + ", " + "\nO seu cadastro foi realizado com sucesso!\nObrigado!");
		enviarEmail.send(mensagemEmail);
	}

}
