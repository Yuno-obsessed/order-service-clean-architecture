package sanity.nil.order.infrastructure.storage.directories;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.dto.FileData;
import sanity.nil.order.application.common.exceptions.StorageException;
import sanity.nil.order.application.common.interfaces.storage.FileStorage;
import sanity.nil.order.infrastructure.storage.config.MinioConfig;

@RequiredArgsConstructor
public class UserStorage implements FileStorage {

    private final MinioConfig minio;

    @Override
    public String createBucketIfNotExists(String... args) {
        String bucketName = "user." + this.constructBucketName(args);
        try {
            if (!minio.getClient().bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build())) {
                minio.getClient().makeBucket(MakeBucketArgs.builder()
                        .build());
            }
        } catch (Exception e) {
            throw new StorageException(e);
        }
        return bucketName;
    }

    @Override
    public void saveFile(FileData file, String name, String bucketName) {

    }

    @Override
    public String getFileURL(String name, String bucketName) {
        return null;
    }

}
