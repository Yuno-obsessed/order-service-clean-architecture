package sanity.nil.library.web.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import sanity.nil.library.config.Services;
import sanity.nil.library.web.dto.AccessCommandDTO;
import sanity.nil.library.web.dto.AccessDTO;
import sanity.nil.library.web.interfaces.WebTemplate;

@Slf4j
public class AuthWebTemplate implements WebTemplate<AccessDTO, AccessCommandDTO> {

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
            .baseUrl(Services.getAuthServiceBaseURL()).build();

    public Mono<AccessDTO> resolveApi(AccessCommandDTO accessCommandDTO) {
        return webClient.post()
                .uri("/access")
                .header("Origin", "http://localhost:5173")
                .header("Access-Control-Request-Method", "POST")
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
