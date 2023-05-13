package Controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * This class is responsible for sending OTP to the user
 * It generates a random 4 digit number and sends it to the user's email
 */
public class OTPSender {

    /**
     * @return the generated OTP
     * This method generates a random 4 digit number
     */
    public static int generateOTP() {
        int randomPin   =(int)(Math.random()*9000)+1000;
        return randomPin;
    }

    /**
     * @param to the user email
     * @param from the email from which the OTP is sent 
     * @param subject the subject of the email
     * @param text the text of the email
     * @return
     * This method sends the OTP to the user's email
     */
    public  boolean sendEmail(String to, String from, String subject, String text) {
        boolean flag = false;

        //logic
        //smtp properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "tofee.store";
        String password = "ajbdaagpdgsyiiij";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }

}

