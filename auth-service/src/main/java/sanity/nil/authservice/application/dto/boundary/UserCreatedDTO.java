package sanity.nil.authservice.application.dto.boundary;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedDTO {

    public String userID;

    public List<String> roles;
}
