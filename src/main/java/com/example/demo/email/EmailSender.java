package com.example.demo.email;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

    public void send() {
        // Recipient's email ID needs to be mentioned.
        String to = "nitesh_iitr@yahoo.com";

        // Sender's email ID needs to be mentioned
        String from = "nitesh_iitr@yahoo.com";
        final String username = "nitesh_iitr";//change accordingly
        final String password = "ja1gurudeV@1310";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        session.setDebug(true);
        System.out.print("Setting up Gmail properties and authentication creds.");

        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Image Inline Subject");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart1 = new MimeBodyPart();
            String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
            messageBodyPart1.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart1);

            // second part (the image)
            BodyPart messageBodyPart2 = new MimeBodyPart();
            DataSource fds = new FileDataSource(
                    "D:\\\\img\\a.png");

            messageBodyPart2.setDataHandler(new DataHandler(fds));
            messageBodyPart2.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart2);

            // put everything together
            message.setContent(multipart);
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully.... to user = " + username + " with \n password=" + password );

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
