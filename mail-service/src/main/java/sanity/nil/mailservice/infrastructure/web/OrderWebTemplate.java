package sanity.nil.mailservice.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.mailservice.application.dto.boundary.ProductImageDTO;
import sanity.nil.mailservice.application.interfaces.WebTemplate;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class OrderWebTemplate implements WebTemplate<ProductImageDTO, UUID> {

    private final WebClient webClient;

    public Mono<ProductImageDTO> resolveApi(UUID productID) {
        return webClient.get()
                .uri("/product/images/{}?onlyMain=true", productID)
                .retrieve()
                .bodyToMono(ProductImageDTO.class);
    }

    @Override
    public ProductImageDTO get(UUID uuid) {
        return resolveApi(uuid).block();
    }

}
