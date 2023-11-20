package sanity.nil.mailservice.application.interfaces;

import jakarta.mail.MessagingException;
import sanity.nil.mailservice.application.consts.MailType;
import sanity.nil.mailservice.application.dto.mail.MailDTO;

public interface MailSender {

    MailDTO sendSimpleEmail(String[] to, MailType subject, String body);
}
