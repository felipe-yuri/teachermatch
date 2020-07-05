package br.com.eductus.teachermatch.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	@Value("${mail.protocol}")
	private String protocol;
	
	@Value("${mail.Host}")
	private String host;
	
	@Value("${mail.port}")
	private int port;
	
	@Value("${mail.smtp.socketFactory.port}")
	private int socketPort;
	
	@Value("${mail.smtp.auth}")
	private boolean auth;
	
	@Value("${mail.smtp.starttls.enable}")
	private boolean starttls;
	
	@Value("${mail.smtp.starttls.required}")
	private boolean startlls_required;
	
	@Value("${mail.smtp.debug}")
	private boolean debug;
	
	@Value("${mail.smtp.socketFactory.fallback}")
	private boolean fallback;
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;

	@Bean
	public JavaMailSender getJavaMailSender() {

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailProperties.put("mail.smtp.starttls.required", startlls_required);
        mailProperties.put("mail.smtp.socketFactory.port", socketPort);
        mailProperties.put("mail.smtp.debug", debug);
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.ssl.checkserveridentity", true); 
        mailProperties.put("mail.smtp.socketFactory.fallback", fallback);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        
		return mailSender;
	}

	@Bean
	public SimpleMailMessage emailTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("somebody@gmail.com");
		message.setFrom("admin@gmail.com");
		message.setText("FATAL - Application crash. Save your job !!");
		return message;
	}
}
