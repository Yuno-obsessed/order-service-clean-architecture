package sanity.nil.userservice.presentation.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sanity.nil.userservice.application.exceptions.UserNotFoundException;
import sanity.nil.userservice.infrastructure.database.models.UserModel;
import sanity.nil.userservice.infrastructure.database.orm.UserORM;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserORM userORM;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userORM.findByEmail(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
        return new UserDetailsImpl(user);
    }
}
