package sanity.nil.order.domain.order.entity;


import sanity.nil.order.domain.order.vo.AddressID;

public class Address {

    private AddressID addressID;
    private String country;
    private String city;
    private String streetName;
    private Integer buildingNumber;
    private String postalCode;

    public Address(AddressID addressID, String country, String city, String streetName,
                   Integer buildingNumber, String postalCode) {
        this.addressID = addressID;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
    }

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder()
                .append(country)
                .append(',')
                .append(city)
                .append(',')
                .append(streetName)
                .append(',')
                .append(',')
                .append(buildingNumber)
                .append(',')
                .append(postalCode);
        return sb.toString();
    }

    public AddressID getAddressID() {
        return addressID;
    }

    public void setAddressID(AddressID addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
