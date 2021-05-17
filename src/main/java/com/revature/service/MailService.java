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

	public static void sendMail(String recipient, String link) throws Exception {
		System.out.println("preparing to send email");
		
		System.out.println("recipient is " +recipient);
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = "customercare.bookstore@gmail.com"; 
		String password = "newPassw0rd";
		
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, myAccountEmail, recipient, link);
		
		Transport.send(message);
		System.out.println("Message sent successfully");
	}
	
	private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String link) {
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Link to reset password");
			String htmlCode = "<p>Hello,</p>"
		            + "<p>You have requested to reset your password.</p>"
		            + "<p>Click the link below to change your password:</p>"
		            + "<p><a href=\"" + link + "\">Change my password</a></p>"
		            + "<br>"
		            + "<p>Ignore this email if you do remember your password, "
		            + "or you have not made the request.</p>"+
					"<br><br>Regards,<br>Bookstore Team";
			message.setContent(htmlCode, "text/html");
			return message;
		}
		catch(Exception e) {
			Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null , e);
			
			e.printStackTrace();
		}
			return null;
		
	}

}
