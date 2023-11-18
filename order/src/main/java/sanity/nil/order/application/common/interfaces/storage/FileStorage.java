package sanity.nil.order.application.common.interfaces.storage;

import sanity.nil.order.application.common.dto.FileData;

public interface FileStorage {

    String createBucketIfNotExists(String... args);

    void saveFile(FileData file, String name, String bucketName);

    String getFileURL(String name, String bucketName);

    default String constructBucketName(String... args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i].toLowerCase());
            if (i < args.length - 1) {
                sb.append('.');
            }
        }
        return sb.toString();
    }

}
