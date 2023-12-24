package sanity.nil.authservice.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {

    @JsonProperty(value = "http_status", required = true)
    public HttpStatus httpStatus;

    @JsonProperty(value = "message")
    public String message;

    public ErrorDTO(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
