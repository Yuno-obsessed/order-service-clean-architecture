package sanity.nil.authservice.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorityCommandDTO {

    public RegisterCommandDTO registerCommandDTO;
    public String userAgent;
    public String ip;
}
