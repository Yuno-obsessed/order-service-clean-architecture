package sanity.nil.tourservice.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "city")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CityModel {

    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    private CountryModel country;

//    @OneToMany(mappedBy = "cityModel")
//    private List<Image> images = new ArrayList<>();
//
//    @OneToMany(mappedBy = "city")
//    private List<Review> reviews = new ArrayList<>();
//
//    @OneToMany(mappedBy = "cityModel")
//    private List<Sight> sights = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityModel cityModel = (CityModel) o;
        return cityId.equals(cityModel.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}
