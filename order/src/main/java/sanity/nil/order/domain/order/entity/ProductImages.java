package sanity.nil.order.domain.order.entity;

import java.util.List;

public class ProductImages {

    private List<String> imageNames;
    private String bucketName;

    public ProductImages(List<String> imageNames, String bucketName) {
        this.imageNames = imageNames;
        this.bucketName = bucketName;
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public String getBucketName() {
        return bucketName;
    }
}
