package sanity.nil.order.application.common.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@NoArgsConstructor
@Getter
public class FileData {

    private InputStream content;
    private String filename;
    private String extension;
    private String type;
    private long size;

    public FileData(InputStream content, String originalFilename, String type, long size) {
        this.content = content;
        if (originalFilename != null) {
            int lastDotIndex = originalFilename.lastIndexOf('.');
            if (lastDotIndex != -1) {
                this.extension = originalFilename.substring(lastDotIndex + 1);
                this.filename = originalFilename.substring(0, lastDotIndex);
            }
        }
        this.type = type;
        this.size = size;
    }

}
