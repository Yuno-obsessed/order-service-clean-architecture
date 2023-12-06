package sanity.nil.mailservice.application.dto.mail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {

    public String topic;
    public String mailContent;
    public String recipients;
    public LocalDateTime sendAt;

    public void setRecipients(String[] recipients){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipients.length; i++) {
            sb.append(recipients[i]);
            if (recipients.length-1 != i) {
                sb.append(',');
            }
        }
        this.recipients = sb.toString();
    }
}
