package sanity.nil.library.web.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import sanity.nil.library.config.Services;
import sanity.nil.library.web.dto.PermissionQueryDTO;
import sanity.nil.library.web.dto.RolePermissionDTO;
import sanity.nil.library.web.interfaces.WebTemplate;

@Slf4j
public class RoleWebTemplate implements WebTemplate<RolePermissionDTO, PermissionQueryDTO> {

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
            .baseUrl(Services.getRoleServiceBaseURL()).build();

    public Mono<RolePermissionDTO> resolveApi(PermissionQueryDTO permissionQueryDTO) {
        return webClient.get()
                .uri(String.format("/permission?uri=%s&verb=%s&roles=%s",
                        permissionQueryDTO.uri, permissionQueryDTO.verb, permissionQueryDTO.roles))
                .header("Origin", "http://localhost:5173")
                .header("Access-Control-Request-Method", "GET")
                .retrieve()
                .bodyToMono(RolePermissionDTO.class);
    }

    @Override
    public RolePermissionDTO get(PermissionQueryDTO dto) {
        return resolveApi(dto)
                .block();
    }
}
