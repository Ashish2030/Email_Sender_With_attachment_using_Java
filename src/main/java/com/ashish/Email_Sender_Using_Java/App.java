package com.ashish.Email_Sender_Using_Java;
import java.io.File;
import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App 
{
	
	public static void main( String[] args )
    {
		System.out.println("preparing to send message ...");
		String message = "Happy Coding  ";
		String subject = "Checking my Code";
		String to = "rs8657511@gmail.com";
		String from = "Kumarashish79924@gmail.com";
	
		sendAttachment(message,subject,to,from);
	}
private static void sendAttachment(String message, String subject, String to, String from) {

	//Variable for gmail
	String host="smtp.gmail.com";
	
	//get the system properties
	Properties properties = System.getProperties();
	System.out.println("PROPERTIES "+properties);
	
	//setting important information to properties object
	
	//host set
	properties.put("mail.smtp.host", host);
	properties.put("mail.smtp.port","465");
	properties.put("mail.smtp.ssl.enable","true");
	properties.put("mail.smtp.auth","true");
	
	//Step 1: to get the session object..
	Session session=Session.getInstance(properties, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {				
			return new PasswordAuthentication("kumarashish79924@gmail.com",                  "Chitkara@123");
		}	
	});
	
	session.setDebug(true);
	
	//Step 2 : compose the message [text,multi media]
	
	MimeMessage m = new MimeMessage(session);
	try {
	
	//from email
	m.setFrom(from);
	
	//adding recipient to message
	m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
	//adding subject to message
	m.setSubject(subject);

	
	//attach
	
	//file path
	String path="C:\\Users\\Ashish\\Desktop\\image\\hacker.jpg";
	
	
	MimeMultipart mimeMultipart = new MimeMultipart();
	MimeBodyPart textMime = new MimeBodyPart();//text
	MimeBodyPart fileMime = new MimeBodyPart();//file
	
	try {
		
		textMime.setText(message); //add text in it
		
		File file=new File(path);
		fileMime.attachFile(file);  //add file path in it  
		
		mimeMultipart.addBodyPart(textMime);  //add both in its parent
		mimeMultipart.addBodyPart(fileMime);  //add both in its parent
	
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	m.setContent(mimeMultipart);
	
	
	//send 
	
	//Step 3 : send the message using Transport class
	Transport.send(m);
	
	
	
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	System.out.println("Sent success...................");
	
	
	

}
}
