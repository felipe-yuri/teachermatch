package br.com.eductus.teachermatch.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.eductus.teachermatch.entities.Mentores;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender enviarEmail;
	
	@Value("${mail.username}")
	private String emailMentoriza;
	
	public void emailCadastroOk(String nome, String para, String assunto, Mentores mentores) throws MessagingException {
		
		MimeMessage mimeMessage = enviarEmail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		String corpoEmail = "<!DOCTYPE html>" +
		"<html>" +
		"<head>" +
		"  <title>Mentoriza</title>" +
		"	<meta charset=\"UTF-8\"/>" +
		"	<meta name=\"viewport\" content=\"width = device-width, initial-scale = 1\" />" +
		"</head>" +
		"<body class=\"container\">" +
		"" +
		"	<div class=\"card-panel\" align=\"center\" style=\"background-color: #0c80ac;\">" +
		"		<h1 style=\"font-size:4vw;\"><font face=\"Holtwood One SC, serif\" color=\"white\">MENTORIZA</font></h1>" +
		"	</div>" +
		"	<br>" +
		"	<div class=\"row\">" +
		"		<div>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Olá <b>" + nome +"</b>, o seu cadastro foi finalizado com sucesso.</p><br>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">De acordo com os nossos critérios avaliativos, você obteve uma pontuação igual a <b>"+ mentores.getPontuacao() +" pontos</b>.</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\"><b>Parabéns!</b> Agora é só correr atrás.</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Fique atento para novas informações. Bons estudos! E até em breve.</p>" +
		"		</div>" +
		"		<br>" +
		"	</div>" +
		"</body>" +
		"</html>";
		
		helper.setText(corpoEmail, true); 
		helper.setTo(para);
		helper.setFrom(emailMentoriza);
		helper.setSubject(assunto);
		enviarEmail.send(mimeMessage);
	}
}
