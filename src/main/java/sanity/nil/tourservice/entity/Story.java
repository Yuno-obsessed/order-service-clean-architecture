package sanity.nil.tourservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "story")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Story {

    @Id
    @Column(name = "story_id")
    public UUID storyId;

    @Column(name = "text", length = 1000)
    public String text;

    @OneToMany(mappedBy = "story")
    public List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sightId")
    public Sight sight;
}
