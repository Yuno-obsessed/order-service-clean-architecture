package sanity.nil.library.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import sanity.nil.library.middleware.AuthenticationFilter;
import sanity.nil.library.middleware.AuthorizationFilter;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.library.web.dto.AccessCommandDTO;
import sanity.nil.library.web.dto.AccessDTO;
import sanity.nil.library.web.dto.PermissionQueryDTO;
import sanity.nil.library.web.dto.RolePermissionDTO;
import sanity.nil.library.web.impl.AuthWebTemplate;
import sanity.nil.library.web.impl.RoleWebTemplate;
import sanity.nil.library.web.interfaces.WebTemplate;

@Configuration
public class BeanCreator {

    @Bean("myObjectMapper")
    public ObjectMapper myObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public WebTemplate<RolePermissionDTO, PermissionQueryDTO> roleWebTemplate() {
        return new RoleWebTemplate();
    }

    @Bean
    public WebTemplate<AccessDTO, AccessCommandDTO> authWebTemplate() {
        return new AuthWebTemplate();
    }

    @Bean
    @Lazy
    public AuthenticationFilter authenticationFilterCreator(WebTemplate<AccessDTO, AccessCommandDTO> webTemplate,
                                                     @Qualifier("myObjectMapper") ObjectMapper objectMapper,
                                                            IdentityProvider identityProvider) {
        return new AuthenticationFilter(webTemplate, objectMapper, identityProvider);
    }

    @Bean
    @Lazy
    public AuthorizationFilter authorizationFilterCreator(WebTemplate<RolePermissionDTO, PermissionQueryDTO> webTemplate,
                                                          @Qualifier("myObjectMapper") ObjectMapper objectMapper,
                                                          IdentityProvider identityProvider) {
        return new AuthorizationFilter(webTemplate, objectMapper, identityProvider);
    }

}
