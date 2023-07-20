package sanity.nil.tourservice.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@With
@Entity
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class CountryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<CityModel> cities = new ArrayList<>();

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryModel countryModel = (CountryModel) o;
        return countryId.equals(countryModel.countryId) &&
                name.equals(countryModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, name);
    }
}
