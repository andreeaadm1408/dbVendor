package ro.db.vendor.errors;

public class MailSenderException extends Exception{


  public MailSenderException(String message) {
    super(message);
  }

  public MailSenderException(String message, Throwable cause) {
    super(message, cause);
  }
}
