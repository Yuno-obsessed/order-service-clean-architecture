package sanity.nil.library.web.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.library.web.dto.PermissionQueryDTO;
import sanity.nil.library.web.dto.RolePermissionDTO;
import sanity.nil.library.web.interfaces.WebTemplate;

@Slf4j
public class RoleWebTemplate implements WebTemplate<RolePermissionDTO, PermissionQueryDTO> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://role-service:8004/api/v1").build();

    public Mono<RolePermissionDTO> resolveApi(PermissionQueryDTO permissionQueryDTO) {
        return webClient.get()
                .uri(String.format("/role/permission?port=%s&uri=%s&roles=%s&method=%s", permissionQueryDTO.port,
                        permissionQueryDTO.uri, permissionQueryDTO.roles, permissionQueryDTO.method))
                .retrieve()
                .bodyToMono(RolePermissionDTO.class);
    }

    @Override
    public RolePermissionDTO get(PermissionQueryDTO dto) {
        return resolveApi(dto)
                .block();
    }
}
