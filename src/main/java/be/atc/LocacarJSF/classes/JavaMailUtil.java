package be.atc.LocacarJSF.classes;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Younes Arifi
 * for send email
 */
public class JavaMailUtil {

    public static Logger log = Logger.getLogger(JavaMailUtil.class);

    public static void sendMail(String recepient) throws Exception {

        log.info("Preparing to send email");

        String myAccountEmail = Constants.MYACCOUNTEMAIL;
        String password = Constants.MYPASSWORDEMAIL;

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", Constants.SMTPHOST);
        properties.put("mail.smtp.port", Constants.SMTPPORT);
        properties.put("mail.smtp.socketFactory.class", Constants.SMTPSOCKETFACTORYCLASS);
        properties.put("mail.smtp.socketFactory.port", Constants.SMTPSOCKETFACTORYPORT);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient);

        Transport.send(message);
        log.info("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My first email from Locacar");
            message.setText("Bonjour, \n Test d'email");
            return message;
        } catch (Exception e) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.ERROR, null, e);
        }
        return null;
    }
}
