package sanity.nil.mailservice.presentation.api;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanity.nil.mailservice.application.interfaces.MailSender;

@RestController
@RequestMapping(value = "/api/v1/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailSender mailSender;

    @GetMapping("/send")
    public ResponseEntity<String> sendMail() {
        try {
            mailSender.sendSimpleEmail("d.2510086@gmail.com", "topic-1", "some text");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body("success");
    }
}
