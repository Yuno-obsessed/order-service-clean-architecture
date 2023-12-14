package sanity.nil.order.infrastructure.storage.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MinioConfig {

    private MinioClient minioClient;

    public MinioConfig(String host, String accessKey, String secretKey) {
        String endpoint = "http://" + host + ":9000";
        log.info("Minio client started at url {}", endpoint);
        minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    public MinioClient getClient() {
        return minioClient;
    }
}
