package sanity.nil.authservice.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateRefreshTokenDTO {

    public LoginCommandDTO loginCommand;
    public String userAgent;
    public String ip;
}
