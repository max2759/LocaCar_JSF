package be.atc.LocacarJSF.classes;

import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Locale;
import java.util.Properties;

/**
 * @author Younes Arifi
 * for send email
 */
public class JavaMailUtil {

    public static Logger log = Logger.getLogger(JavaMailUtil.class);

    public static void sendMail(OrdersEntity ordersEntity) throws Exception {

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

        Message message = prepareMessage(session, myAccountEmail, ordersEntity);

        Transport.send(message);
        log.info("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, OrdersEntity ordersEntity) {

        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        String filename = ordersEntity.getUsersByIdUsers().getFirstname() + "-" + ordersEntity.getUsersByIdUsers().getLastname() + "_" + "Order" + ordersEntity.getId() + "_" + ordersEntity.getOrderStatut() + ".pdf";
        String sourcePath = Constants.FILE_OUT_PUT_STREAM + filename;

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(ordersEntity.getUsersByIdUsers().getEmail()));
            message.setSubject(JsfUtils.returnMessage(locale, "mail.Subject"));

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(JsfUtils.returnMessage(locale, "mail.hello") + "\n\n" + JsfUtils.returnMessage(locale, "mail.body") + "\n\n" + JsfUtils.returnMessage(locale, "mail.thankU"));

            MimeBodyPart pdfAttachement = new MimeBodyPart();
            pdfAttachement.attachFile(sourcePath);

            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachement);

            message.setContent(emailContent);
            return message;
        } catch (Exception e) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.ERROR, null, e);
        }
        return null;
    }
}
