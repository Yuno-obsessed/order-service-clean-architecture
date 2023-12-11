package sanity.nil.order.infrastructure.storage.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class MinioConfig {

    private MinioClient minioClient;

    @Value("${application.minio.host}")
    private String minioHost;

    public MinioConfig(String accessKey, String secretKey) {
        minioClient = MinioClient.builder()
                .endpoint("http://" + minioHost + ":9000")
                .credentials(accessKey, secretKey)
                .build();
    }

    public MinioClient getClient() {
        return minioClient;
    }
}
