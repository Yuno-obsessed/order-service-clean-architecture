package sanity.nil.mailservice.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import sanity.nil.mailservice.application.dto.boundary.ProductImageDTO;
import sanity.nil.mailservice.application.interfaces.WebTemplate;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class OrderWebTemplate implements WebTemplate<List<ProductImageDTO>, UUID> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/api/v1").build();

    public Flux<ProductImageDTO> resolveApi(UUID productID) {
        return webClient.get()
                .uri(String.format("/product/images/%s?onlyMain=true", productID.toString()))
                .retrieve()
                .bodyToFlux(ProductImageDTO.class);
    }

    @Override
    public List<ProductImageDTO> get(UUID uuid) {
        return resolveApi(uuid)
                .collectList()
                .block();
    }

}
