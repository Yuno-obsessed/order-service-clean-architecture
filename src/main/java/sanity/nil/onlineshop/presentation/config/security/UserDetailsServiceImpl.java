package sanity.nil.onlineshop.presentation.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sanity.nil.onlineshop.infrastructure.database.model.UserModel;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.build(new UserModel(UUID.randomUUID(), "sanity", "$2a$10$AKB9ZiNS6lwoEneaKgrgcOxFB/4IT80WuqdAIqQ1cnPBk8Nk9NMkC"));
    }
}
