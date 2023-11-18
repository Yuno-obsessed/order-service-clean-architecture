package sanity.nil.mailservice.application.interfaces;

import jakarta.mail.MessagingException;

public interface MailSender {

    void sendSimpleEmail(String to, String subject, String body) throws MessagingException;
}
