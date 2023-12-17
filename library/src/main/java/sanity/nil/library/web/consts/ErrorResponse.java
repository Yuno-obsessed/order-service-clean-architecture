package sanity.nil.library.web.consts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @JsonProperty(value = "request_id", required = true)
    private UUID requestId;

    @JsonProperty(value = "error_type", required = false)
    private String errorType;

    @JsonProperty(value = "error_reason", required = true)
    private String errorReason;

    @JsonProperty(value = "timestamp", required = true)
    private String timestamp;

    public ErrorResponse(UUID requestId, String errorReason) {
        this.requestId = requestId;
        this.errorReason = errorReason;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }


    public ErrorResponse(UUID requestId, String errorReason, String errorType) {
        this.requestId = requestId;
        this.errorType = errorType;
        this.errorReason = errorReason;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }}
