package sanity.nil.mailservice.infrastructure.database.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "mail_history", schema = "mail_service")
public class MailHistoryModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "mail_content", columnDefinition = "text")
    private String mailContent;

    @Column(name = "recipients")
    private String recipients;

    @Column(name = "send_at", columnDefinition = "timestamptz")
    private LocalDateTime sendAt;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate() {
       this.createdAt = LocalDateTime.now();
    }

    public MailHistoryModel(UUID id, String topic, String mailContent, String recipients, LocalDateTime sendAt) {
        this.id = id;
        this.topic = topic;
        this.mailContent = mailContent;
        this.recipients = recipients;
        this.sendAt = sendAt;
    }
}
