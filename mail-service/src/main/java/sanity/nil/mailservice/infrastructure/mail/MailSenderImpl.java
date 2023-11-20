package sanity.nil.mailservice.infrastructure.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import sanity.nil.mailservice.application.consts.MailType;
import sanity.nil.mailservice.application.dto.mail.MailDTO;
import sanity.nil.mailservice.application.exceptions.MailingException;
import sanity.nil.mailservice.application.interfaces.MailSender;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;

    public MailDTO sendSimpleEmail(String[] to, MailType subject, String body) {

        MimeMessage message = mailSender.createMimeMessage();
        MailDTO mailDTO = new MailDTO();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject.getValue());
            helper.setText(body, true);
            mailSender.send(message);
            mailDTO.sendAt = LocalDateTime.now();
            mailDTO.topic = subject.getValue();
            mailDTO.mailContent = body;
            mailDTO.setRecipients(to);
        } catch (Exception e) {
            throw new MailingException(e);
        }
        return mailDTO;
    }

}
