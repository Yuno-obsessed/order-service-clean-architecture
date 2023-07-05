package sanity.nil.tourservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Country {

    @Id
    @Column(name = "country_id")
    public Integer countryId;

    @Column(name = "description", nullable = false, length = 500)
    public String description;

    @Column(name = "name", nullable = false, length = 100)
    public String name;

    @OneToMany(mappedBy = "country")
    public List<City> cities = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countryId.equals(country.countryId) &&
                name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, name);
    }
}
