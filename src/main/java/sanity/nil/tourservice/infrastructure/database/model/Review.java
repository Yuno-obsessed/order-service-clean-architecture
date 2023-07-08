package sanity.nil.tourservice.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Review {

    @Id
    @Column(name = "review_id")
    private UUID reviewId;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tourId")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;
}
