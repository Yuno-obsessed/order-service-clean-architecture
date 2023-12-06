package sanity.nil.authservice.application.dto.boundary;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RefreshTokenDTO {

    public String refreshToken;
    public String userAgent;
    public String ip;
}
