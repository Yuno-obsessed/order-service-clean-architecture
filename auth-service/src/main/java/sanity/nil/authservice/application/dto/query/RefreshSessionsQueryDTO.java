package sanity.nil.authservice.application.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class RefreshSessionsQueryDTO {

    @JsonProperty(value = "session_id")
    public UUID id;

    @JsonProperty(value = "user_id")
    public UUID userID;

    @JsonProperty(value = "refresh_token")
    public String refreshToken;

    @JsonProperty(value = "user_agent")
    public String userAgent;

    @JsonProperty(value = "fingerprint")
    public String fingerprint;

    @JsonProperty(value = "ip")
    public String ip;

    @JsonProperty(value = "created_at")
    public LocalDateTime createdAt;

}
