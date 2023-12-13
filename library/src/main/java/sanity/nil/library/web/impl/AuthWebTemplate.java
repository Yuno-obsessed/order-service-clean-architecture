package sanity.nil.library.web.impl;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.library.config.Services;
import sanity.nil.library.web.dto.AccessCommandDTO;
import sanity.nil.library.web.dto.AccessDTO;
import sanity.nil.library.web.interfaces.WebTemplate;

public class AuthWebTemplate implements WebTemplate<AccessDTO, AccessCommandDTO> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl(new Services().getAuthServiceBaseURL()).build();

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
