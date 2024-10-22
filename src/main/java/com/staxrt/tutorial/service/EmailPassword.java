package com.staxrt.tutorial.service;


    import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service

public class EmailPassword {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587"; // Use port 587 for TLS
    private static final String SMTP_USERNAME = "ramsthelegend9472@gmail.com"; // Replace with your Gmail email
    private static final String SMTP_PASSWORD = "mbwypnbwsqoatgkz"; // Replace with your Gmail password

    public static void sendForgotPasswordEmail(String recipientEmail, String password) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("akilanrm29@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your Forgotten Password - TheSmartSteps");
            message.setText("Dear user,\n\n" +
                    "Your forgotten password is: " + password + "\n\n" +
                    "Please make sure to change your password after logging in.\n\n" +
                    "Best regards,\n" +
                    "Admin\n" +
                    "TheSmartSteps");

            Transport.send(message);

            System.out.println("Forgot password email sent to " + recipientEmail);
        } catch (MessagingException e) {
            System.err.println(e);
            System.err.println("Failed to send forgot password email to " + recipientEmail);
        }
    }
}