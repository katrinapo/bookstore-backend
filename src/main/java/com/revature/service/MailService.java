package com.revature.service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	public static void sendMail(String recipient) throws Exception {
		System.out.println("preparing to send email");
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = " "; //add a bookstore gmail account
		String password = " "; // add bookstore gmail password
		
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, myAccountEmail, recipient);
		
		Transport.send(message);
		System.out.println("Message sent successfully");
	}
	
	private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Reset Password");
			String htmlCode = "<h4>Hi,<br>"
					+ " Please click the this <span><a href='http://localhost:4200/createpassword'>link</a></span> "
					+ "to reset the password.</h4>";
			message.setContent(htmlCode, "text/html");
			return message;
		}
		catch(Exception e) {
			Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null , e);
			
			e.printStackTrace();
		}
			return null;
		
	}
	
//	public static void main(String[] args) throws Exception {
//		MailService.sendMail("shrestha.zenith@gmail.com");
//	}
	
}
