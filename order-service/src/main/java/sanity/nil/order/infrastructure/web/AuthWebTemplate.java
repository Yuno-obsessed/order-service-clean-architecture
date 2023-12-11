package sanity.nil.order.infrastructure.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.order.application.common.dto.AccessCommandDTO;
import sanity.nil.order.application.common.dto.AccessDTO;
import sanity.nil.order.application.order.interfaces.web.WebTemplate;

@Slf4j
public class AuthWebTemplate implements WebTemplate<AccessDTO, AccessCommandDTO> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://auth-service:8003/api/v1").build();

    public Mono<AccessDTO> resolveApi(AccessCommandDTO accessCommandDTO) {
        return webClient.post()
                .uri("/auth/access")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(accessCommandDTO))
                .retrieve()
                .bodyToMono(AccessDTO.class);
    }

    @Override
    public AccessDTO get(AccessCommandDTO dto) {
        return resolveApi(dto)
                .block();
    }
}
