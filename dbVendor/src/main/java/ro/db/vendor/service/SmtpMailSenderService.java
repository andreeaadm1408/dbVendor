package ro.db.vendor.service;

import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class SmtpMailSenderService {

  @Value("${mail.smtp.auth}")
  private Boolean auth;

  @Value("${mail.smtp.starttls.enable}")
  private Boolean starttls;

  @Value("${mail.smtp.host}")
  private String host;

  @Value("${mail.smtp.port}")
  private int port;

  @Value("${mail.userName}")
  private String userName;

  @Value("${mail.password}")
  private String password;

  public void sendVerificationMail(String email, String subject, String employeeName,
      String vendorName) {
    SendMailTLS sendMailTLS = new SendMailTLS(email, subject, employeeName, vendorName);
    Thread th = new Thread(sendMailTLS);
    th.start();
  }

  public class SendMailTLS implements Runnable {

    private String email;
    private String subject;
    private String employeeName;
    private String vendorName;


    public SendMailTLS(String email, String subject, String employeeName, String vendorName) {
      this.email = email;
      this.subject = subject;
      this.employeeName = employeeName;
      this.vendorName = vendorName;
    }

    @Override
    public void run() {

      Properties props = new Properties();
      props.put("mail.smtp.auth", auth);
      props.put("mail.smtp.starttls.enable", starttls);
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", port);

      MailSSLSocketFactory sf = null;
      try {
        sf = new MailSSLSocketFactory();
      } catch (GeneralSecurityException e1) {

        e1.printStackTrace();
      }
      sf.setTrustAllHosts(true);
      props.put("mail.smtp.ssl.socketFactory", sf);

      Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(userName, password);
            }
          });

      try {
        log.debug("Sending email to: {}" + email);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(this.email));
        message.setSubject(this.subject);

        message.setContent("<html>" + "<head>" +
            "<style>\n" + "\n" + "</style>" + "</head>" + "<body style='text-align:center;'>"
            + "<br>" + "<br>"
            + "<h2 style='text-align:left;'>" + "Hello " + this.employeeName + "," + "</h2>"
            + "<br>" +
            "<h2 style='text-align:left;'>"
            + "Your manager has asked you to give feedback to your colleague, "
            + this.vendorName + "." + "</h2>" + "</a>" + "<br>" + "<br>" +
            "<h2 style='text-align:left;'>" + "Thank you!" + "<br>" + "<br>" + "Db Team"
            + "</body>", "text/html");
        Transport.send(message);

      } catch (MessagingException e) {
        log.error("An error occurred while trying to sent the email with message: ", e);
      }
    }
  }

}