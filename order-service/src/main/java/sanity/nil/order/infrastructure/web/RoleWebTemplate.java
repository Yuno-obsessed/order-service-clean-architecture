package sanity.nil.order.infrastructure.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sanity.nil.order.application.order.dto.query.PermissionQueryDTO;
import sanity.nil.order.application.order.interfaces.web.WebTemplate;

@Slf4j
public class RoleWebTemplate implements WebTemplate<Boolean, PermissionQueryDTO> {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://role-service:8004/api/v1").build();

    public Mono<Boolean> resolveApi(PermissionQueryDTO permissionQueryDTO) {
        return webClient.get()
                .uri(String.format("/role/permission?port=%s&uri=%s&roles=%s", permissionQueryDTO.port,
                        permissionQueryDTO.uri, permissionQueryDTO.roles))
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Boolean get(PermissionQueryDTO dto) {
        return resolveApi(dto)
                .block();
    }
}
