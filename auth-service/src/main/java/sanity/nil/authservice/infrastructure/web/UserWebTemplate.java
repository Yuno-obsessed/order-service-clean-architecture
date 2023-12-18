package sanity.nil.authservice.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.authservice.application.dto.boundary.UserDTO;
import sanity.nil.authservice.application.dto.boundary.UserIDQueryDTO;
import sanity.nil.authservice.application.interfaces.WebTemplate;

@Slf4j
@RequiredArgsConstructor
public class UserWebTemplate implements WebTemplate<UserIDQueryDTO, UserDTO> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://user-service:8002/api/v1/users").build();

    public Mono<UserIDQueryDTO> resolveApi(UserDTO userDTO) {
        return webClient.post()
                .uri("/user/get")
                .header("Origin", "http://localhost:5173")
                .header("Access-Control-Request-Method", "POST")
                .body(BodyInserters.fromValue(userDTO))
                .retrieve()
                .bodyToMono(UserIDQueryDTO.class);
    }

    @Override
    public UserIDQueryDTO get(UserDTO userDTO) {
        return resolveApi(userDTO).block();
    }

}
