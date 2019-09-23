package javamail;
//
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//
public class JavaMail {
    //ДЗ 1 Настроить свой почтовый ящик гмел на отправку почты
    private static String USER_NAME = "azure.itstep24.kharkov";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "Azure30$13"; // GMail password
    private static String RECIPIENT1 = "artem.konstantinovich@ukr.net";
    private static String RECIPIENT2 = "artem.chistyakov@gmail.com";

    public static void main(String[] args) 
    {
        // TODO code application logic here
        
    }
    //
        private static void sendFromGMail(String from, 
            String pass, String[] to, String subject, String body)
    {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        // Сессия соединения с Вэб сервером
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    //
}
