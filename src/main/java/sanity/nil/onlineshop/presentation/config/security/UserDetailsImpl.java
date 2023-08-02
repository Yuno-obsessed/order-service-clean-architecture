package sanity.nil.onlineshop.presentation.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sanity.nil.onlineshop.infrastructure.model.UserModel;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String username;

    private String password;

//    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserModel user) {
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
