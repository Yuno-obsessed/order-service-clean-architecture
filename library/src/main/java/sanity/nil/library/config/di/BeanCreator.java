package sanity.nil.library.config.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sanity.nil.library.filter.AuthenticationFilter;
import sanity.nil.library.web.dto.AccessCommandDTO;
import sanity.nil.library.web.dto.AccessDTO;
import sanity.nil.library.web.impl.AuthWebTemplate;
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

//    @Bean
//    public WebTemplate<Boolean, PermissionQueryDTO> roleWebTemplate() {
//        return new RoleWebTemplate();
//    }

    @Bean
    public WebTemplate<AccessDTO, AccessCommandDTO> authWebTemplate() {
        return new AuthWebTemplate();
    }

    @Bean
    public AuthenticationFilter authenticationFilterCreator(WebTemplate<AccessDTO, AccessCommandDTO> webTemplate,
                                                     @Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        return new AuthenticationFilter(webTemplate, objectMapper);
    }
}
