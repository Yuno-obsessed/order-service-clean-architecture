package sanity.nil.order.application.product.dto.boundary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sanity.nil.order.application.common.dto.FileData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProductFileData {

    private UUID productID;
    private List<FileData> files;

    public List<String> getFileNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            names.add(productID.toString() + '-' + i);
        }
        return names;
    }
}
