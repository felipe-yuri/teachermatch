package br.com.eductus.teachermatch.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.eductus.teachermatch.entities.Mentorandos;
import br.com.eductus.teachermatch.entities.Mentores;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender enviarEmail;
	
	@Value("${mail.username}")
	private String emailMentoriza;
	
	public void emailCadastroMentores(String nome, String para, String assunto, Mentores mentores) throws MessagingException {
		
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
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Olá <b>" + nome +"</b>,</p>" + 
		"			<p style=\"text-align: justify; text-justify: inter-word;\">O seu cadastro foi finalizado com sucesso. <b>Parabéns!</b></p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Fique atento para novas informações. Bons estudos e até em breve!</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Atenciosamente,</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Equipe Eductus</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Teste <b>"+ mentores.getPontuacao() +" pontos</b>.</p>" +
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
	
	public void emailCadastroMentorandos(String nome, String para, String assunto, Mentorandos mentorandos) throws MessagingException {
		
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
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Olá <b>" + nome +"</b>,</p>" + 
		"			<p style=\"text-align: justify; text-justify: inter-word;\">O seu cadastro foi finalizado com sucesso. <b>Parabéns!</b></p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Fique atento para novas informações. Bons estudos e até em breve!</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Atenciosamente,</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Equipe Eductus</p>" +
		"			<p style=\"text-align: justify; text-justify: inter-word;\">Teste <b>"+ mentorandos.getPontuacao() +" pontos</b>.</p>" +
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
