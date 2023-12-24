package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "addresses", schema = "order_service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressModel extends BaseModel {

    @Column(name = "user_id", unique = true)
    private UUID userID;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "building_number")
    private Integer buildingNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressModel that = (AddressModel) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(streetName, that.streetName) &&
                Objects.equals(buildingNumber, that.buildingNumber) &&
                Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, streetName, buildingNumber, postalCode);
    }
}
