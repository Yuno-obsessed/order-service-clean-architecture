package sanity.nil.tourservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sight")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Sight {

    @Id
    @Column(name = "sight_id")
    public UUID sightId;

    @Column(name = "name", nullable = false, length = 500)
    public String name;

    @ManyToOne
    @JoinColumn(name = "cityId")
    public City city;

    @OneToMany(mappedBy = "sight")
    public List<Story> stories = new ArrayList<>();

    @OneToMany(mappedBy = "sight")
    public List<Image> images = new ArrayList<>();
}
