package com.smart.service;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String subject, String message, String to) {

		// rest of code
		boolean f = false;
		String from = "rotaractclubmdlclg2016@gmail.com";

		// Validate the email address using a regular expression
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(to);

		if (!matcher.matches()) {
			System.out.println("Invalid email address: " + to);
			System.out.println("INVALID EMAIL ADDRESS..............");
			return false;
		}

		// Variable for gmail
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		System.out.println("PROPERTIES: " + properties);

		// Setting Important information to properties object

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		// properties.put("mail.smtp.starttls.enable", "true");
		// properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		// Step 1 : To get the session object
		// Create a Session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("rotaractclubmdlclg2016@gmail.com", "uudkzrjxfejxrtou");
			}
		});

		session.setDebug(true);

		// Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {

			// from email
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			// m.setText(message);
			m.setContent(message, "text/html");

			// send

			// Step 3 : send the message using Transport class
			Transport.send(m);
			f = true; // Set f to true if the email is sent successfully
			System.out.println("Sent success...................");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sending failed...................");
			return false;
		}
		return f;

	}

}
