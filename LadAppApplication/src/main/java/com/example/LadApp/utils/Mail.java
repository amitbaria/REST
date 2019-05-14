package com.example.LadApp.utils;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

/*	public static void main(String args[]) throws AddressException,
			MessagingException {

		Mail javaEmail = new Mail();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage();
		javaEmail.sendEmail();
	}
*/
	  
	private String email,msessageBodyLink,userName;
	
	
	public Mail(String userName,String email,String msessageBodyLink)
	{
		this.email=email;
		this.msessageBodyLink=msessageBodyLink;
		this.userName=userName;
		
		
		  System.out.println(this.userName+" " +email + "  "+this.msessageBodyLink);
		
		
	}
	 public boolean mailTrigger()  throws AddressException,MessagingException
		
	 {
		
		  
		 
			setMailServerProperties();
			createEmailMessage();
			sendEmail();
		 
		     return true;
		    
		 
		 
		 
	 }
	
	
	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage() throws AddressException,
			MessagingException {
		String[] toEmails = { email };
		String emailSubject = "Registration Lad App";
		
		
		
		String emailBody = formattedBody();

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email

	}

	public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "amitbaria.niitnoida";//just the id alone without @gmail.com
		String fromUserEmailPassword = "amitbarianiitnoida";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}
	
	
	public String formattedBody()
	{
		
		    String url="<a href="+msessageBodyLink+">"+ msessageBodyLink+"</a>";
		     StringBuffer buffer=new StringBuffer();
		 
		     buffer.append("Hi ");
		  
		     buffer.append("<b>"+userName+",</b>").append("<br/><br/>");
		     buffer.append("<font face=\"arial\" color=\"#8ebf42\">We Please to inform you that Once you activate or click below mentioned link, You would be activated for Lad Applicatoin</font>").append("<br/><br/>");
		     buffer.append(url);
		
		  
		
	       return	buffer.toString();
		
	}

}