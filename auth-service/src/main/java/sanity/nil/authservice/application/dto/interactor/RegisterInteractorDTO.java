package sanity.nil.authservice.application.dto.interactor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterInteractorDTO {

    public String accessToken;
    public String refreshToken;
    public int refreshMaxAge;
}
