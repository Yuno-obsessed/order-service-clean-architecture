package sanity.nil.order.infrastructure.storage.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MinioConfig {

    private MinioClient minioClient;

    public MinioConfig(String accessKey, String secretKey) {
        minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials(accessKey, secretKey)
                .build();
    }

    public MinioClient getClient() {
        return minioClient;
    }
}
