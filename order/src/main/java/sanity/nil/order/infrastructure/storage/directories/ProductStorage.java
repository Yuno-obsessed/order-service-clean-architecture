package sanity.nil.order.infrastructure.storage.directories;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.common.application.dto.FileData;
import sanity.nil.order.application.common.application.exceptions.StorageException;
import sanity.nil.order.application.common.application.interfaces.storage.FileStorage;
import sanity.nil.order.infrastructure.storage.config.MinioConfig;

@RequiredArgsConstructor
public class ProductStorage implements FileStorage {

    private final MinioConfig minio;

    @Override
    public String createBucketIfNotExists(String... args) {
        String bucketName = "product." + this.constructBucketName(args);
        try {
            if (!minio.getClient().bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build())) {
                minio.getClient().makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
            }
        } catch (Exception e) {
            throw new StorageException(e);
        }
        return bucketName;
    }

    @Override
    public void saveFile(FileData file, String name, String bucketName) {
        try {
            if (minio.getClient().bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build())) {
                minio.getClient().putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(name)
                        .stream(file.getContent(), file.getSize(), -1)
                        .contentType(file.getType())
                        .build());
            }
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public FileData getFile(String name, String bucketName) {
        try {
            minio.getClient().getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name)
                    .build());
        } catch (Exception e) {
            throw new StorageException(e);
        }
        return null;
    }

}
