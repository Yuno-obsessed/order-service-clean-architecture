package sanity.nil.mailservice.infrastructure.database.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "mail_history")
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
}
