package sanity.nil.tourservice.infrastructure.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Question {

    @Id
    @Column(name = "question_id")
    private Integer id;

    @Column(name = "text")
    private String text;
}
