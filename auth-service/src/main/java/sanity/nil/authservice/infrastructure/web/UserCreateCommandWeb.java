package sanity.nil.authservice.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.authservice.application.dto.boundary.UserCreateCommandDTO;
import sanity.nil.authservice.application.dto.boundary.UserCreatedDTO;
import sanity.nil.authservice.application.exceptions.UserServiceException;
import sanity.nil.authservice.application.interfaces.WebTemplate;

@RequiredArgsConstructor
public class UserCreateCommandWeb implements WebTemplate<UserCreatedDTO, UserCreateCommandDTO> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://proxy:80/user-service/user").build();

    public Mono<UserCreatedDTO> resolveApi(UserCreateCommandDTO userCreateCommandDTO) {
        return webClient.post()
                .uri("")
                .header("Origin", "http://localhost:5173")
                .header("Access-Control-Request-Method", "POST")
                .body(BodyInserters.fromValue(userCreateCommandDTO))
                .retrieve()
                .bodyToMono(UserCreatedDTO.class)
                .onErrorMap(error -> {
//                    log.error("An error occurred: {}", error.getMessage());
                    return new UserServiceException(error);
                });
    }

    @Override
    public UserCreatedDTO get(UserCreateCommandDTO userCreateCommandDTO) {
        return resolveApi(userCreateCommandDTO).block();
    }
}
