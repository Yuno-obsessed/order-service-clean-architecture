package sanity.nil.library.services.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class Identity {

    public UUID userID;

    public List<String> roles;
}
