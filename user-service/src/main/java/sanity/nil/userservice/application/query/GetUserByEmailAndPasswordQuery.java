package sanity.nil.userservice.application.query;

import lombok.RequiredArgsConstructor;
import sanity.nil.userservice.application.dto.query.UserQueryDTO;
import sanity.nil.userservice.application.exceptions.PasswordsDoNotMatch;
import sanity.nil.userservice.application.interfaces.persistence.UserReader;
import sanity.nil.userservice.application.interfaces.security.PasswordEncoder;

@RequiredArgsConstructor
public class GetUserByEmailAndPasswordQuery {

    private final UserReader userReader;
    private final PasswordEncoder passwordEncoder;

    public UserQueryDTO handle(String email, String password) {
        UserQueryDTO userQueryDTO = userReader.getUserByEmail(email);
        if (userQueryDTO == null) {
            return null;
        }
        if (passwordEncoder.verifyHash(password, userQueryDTO.password)) {
            return userQueryDTO;
        } else {
            throw new PasswordsDoNotMatch();
        }
    }
}
