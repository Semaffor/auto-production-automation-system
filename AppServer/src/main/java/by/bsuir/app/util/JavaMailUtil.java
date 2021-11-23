package by.bsuir.app.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static by.bsuir.app.util.constants.Constants.*;

/**
 * Class for interaction with mail: send letters to recipients
 */
public class JavaMailUtil {

    public static void send(String recipient, String msg_topic, String msg) throws MessagingException {
        String from = MAIL_USER_NAME;
        String pass = MAIL_PASSWORD;
        String RECIPIENT = recipient;
        String[] to = {RECIPIENT};
        String subject = msg_topic;
        String body = msg;

        sendFromGMail(from, pass, to, subject, body);
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) throws
            MessagingException {
        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", MAIL_HOST);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.port", MAIL_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);



        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = new InternetAddress[to.length];

        for (int i = 0; i < to.length; i++) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for (int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }

        message.setSubject(subject);
        message.setText(body);

        Transport transport = session.getTransport("smtp");

        transport.connect(MAIL_HOST, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }


}