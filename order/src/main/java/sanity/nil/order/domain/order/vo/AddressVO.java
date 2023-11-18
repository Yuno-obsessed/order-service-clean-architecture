package sanity.nil.order.domain.order.vo;

import java.io.Serializable;
import java.util.UUID;

public class AddressVO implements Serializable {

    private UUID addressID;
    private String address;

    public AddressVO(UUID addressID, String address) {
        this.addressID = addressID;
        this.address = address;
    }

    public AddressVO() {
    }

    public String getAddressVOString() {
        return addressID.toString() + ',' + address;
    }

    public String[] getSplitAddress() {
        return address.split(",");
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
