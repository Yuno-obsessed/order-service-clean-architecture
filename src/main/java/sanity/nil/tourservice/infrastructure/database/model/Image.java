package sanity.nil.tourservice.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Image {

    @Id
    @Column(name = "image_id")
    public UUID imageId;

    @Column(name = "path_to_image", nullable = false, length = 500)
    public String pathToImage;

    @ManyToOne
    @JoinColumn(name = "cityId")
    public City city;

    @ManyToOne
    @JoinColumn(name = "sightId")
    public Sight sight;

    @ManyToOne
    @JoinColumn(name = "storyId")
    public Story story;
}
