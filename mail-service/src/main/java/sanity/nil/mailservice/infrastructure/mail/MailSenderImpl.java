package sanity.nil.mailservice.infrastructure.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import sanity.nil.mailservice.application.exceptions.MailingException;
import sanity.nil.mailservice.application.interfaces.MailSender;

@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String body) throws MailingException {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new MailingException(e);
        }
    }

}
