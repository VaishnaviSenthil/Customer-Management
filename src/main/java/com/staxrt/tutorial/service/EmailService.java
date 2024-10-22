package com.staxrt.tutorial.service;

import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587"; // Use port 587 for TLS
    private static final String SMTP_USERNAME = "ramsthelegend9472@gmail.com";
    private static final String SMTP_PASSWORD = "mbwypnbwsqoatgkz";

    public static void sendWelcomeEmail(String recipientEmail) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("User"+SMTP_USERNAME+" "+SMTP_PASSWORD);
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("akilanrm29@gmail.com")); // Replace with your email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(" Welcome to Hibiz Solutions - Exclusive Discounts Await You!");
            message.setText("Dear user,\n\n" + //
                    "\r\n" + //
                    "Welcome to Hibiz Solutions! We're excited to have you join our family. As a special thank you, we're offering you these exclusive benefits:\r\n" + //
                    "\r\n" + //
                    "15% off your first purchase with code: NEW15\r\n" + //
                    "Free shipping on your first three orders\r\n" + //
                    "Earn points for future discounts through our loyalty program\r\n" + //
                    "Get early access to new product previews\r\n" + //
                    "Enjoy a birthday surprise each year\r\n" + //
                    "Start shopping and experiencing these perks today. Feel free to reach out if you need any assistance. Happy shopping!\r\n" + //
                    "\r\n" + //
                    "Best regards,\r\n" + //
                    "Admin\r\n" + //
                    "Hibiz Solutions\r\n" //
                    );

            Transport.send(message);

            System.out.println("Welcome email sent to " + recipientEmail);
        } catch (MessagingException e) {
            // e.printStackTrace();
            System.err.println(e);
            System.err.println("Failed to send welcome email to " + recipientEmail);
        }
    }
}
