package sanity.nil.tourservice.infrastructure.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tour")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Tour implements Cloneable{

    @Id
    @Column(name = "tour_id")
    private UUID tourId;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    private City city;

    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "price", nullable = false)
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return tourId.equals(tour.tourId) &&
                city.equals(tour.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, city);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
