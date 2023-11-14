package sanity.nil.order.application.product.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UploadProductImages {

    @JsonProperty(value = "product_id", required = true)
    private UUID productID;

    private List<MultipartFile> files;
}
