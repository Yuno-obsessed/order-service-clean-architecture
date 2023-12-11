package sanity.nil.authservice.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        return webClient.get()
                .uri(String.format("/user?email=%s&password=%s", userDTO.email, userDTO.password))
                .retrieve()
                .bodyToMono(UserIDQueryDTO.class);
    }

    @Override
    public UserIDQueryDTO get(UserDTO userDTO) {
        return resolveApi(userDTO).block();
    }

}
