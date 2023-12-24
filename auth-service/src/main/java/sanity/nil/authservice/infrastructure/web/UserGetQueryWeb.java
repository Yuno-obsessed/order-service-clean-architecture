package sanity.nil.authservice.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.authservice.application.dto.boundary.UserDTO;
import sanity.nil.authservice.application.dto.boundary.UserIDQueryDTO;
import sanity.nil.authservice.application.exceptions.UserServiceException;
import sanity.nil.authservice.application.interfaces.WebTemplate;

@Slf4j
@RequiredArgsConstructor
public class UserGetQueryWeb implements WebTemplate<UserIDQueryDTO, UserDTO> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://proxy:80/user-service/user").build();

    public Mono<UserIDQueryDTO> resolveApi(UserDTO userDTO) {
        return webClient.post()
                .uri("/get")
                .header("Origin", "http://localhost:5173")
                .header("Access-Control-Request-Method", "POST")
                .body(BodyInserters.fromValue(userDTO))
                .retrieve()
                .bodyToMono(UserIDQueryDTO.class)
                .onErrorMap(error -> {
//                    log.error("An error occurred: {}", error.getMessage());
                    return new UserServiceException(error);
                });
    }

    @Override
    public UserIDQueryDTO get(UserDTO userDTO) {
        return resolveApi(userDTO).block();
    }

}
