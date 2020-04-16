package com.rms.recreativos.mail;

import java.io.File;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Resource
public class MailUtil {

	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public void sendMail(String subject, String body) throws MessagingException {
		sendMail(subject, body, null, null, null, null);
	}
	
	public void sendMail(String subject, String body, Boolean html) throws MessagingException {
		sendMail(subject, body, null, null, null, html);
	}
	
	public void sendMail(String subject, String body, String emailFrom, String[] emailTo) throws MessagingException {
		sendMail(subject, body, null, emailFrom, emailTo, null);
	}
	
	public void sendMail(String subject, String body, String emailFrom, String[] emailTo, Boolean html) throws MessagingException {
		sendMail(subject, body, null, emailFrom, emailTo, html);
	}
	
	public void sendMail(String subject, String body, File fichero) throws MessagingException {
		sendMail(subject, body, fichero, null, null, null);
	}
	
	public void sendMail(String subject, String body, File fichero, Boolean html) throws MessagingException {
		sendMail(subject, body, fichero, null, null, html);
	}
	
	public void sendMail(String subject, String body, File fichero, String emailFrom, String[] emailTo, Boolean html) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(emailFrom != null?emailFrom:simpleMailMessage.getFrom());
		helper.setTo(emailTo != null?emailTo:simpleMailMessage.getTo());
		helper.setSubject(subject);
		if (html != null){
			helper.setText(body, html.booleanValue());
		} else {
			helper.setText(body);
		}
		if (fichero != null){
			FileSystemResource file = new FileSystemResource(fichero);
			helper.addAttachment(file.getFilename(), file);
		}
		try{
			mailSender.send(message);
		} catch(MailException me){
			me.printStackTrace();
			throw new MessagingException(me.getMessage(), me);
		}
	}
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}