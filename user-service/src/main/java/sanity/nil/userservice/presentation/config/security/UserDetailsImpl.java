package sanity.nil.userservice.presentation.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sanity.nil.userservice.infrastructure.database.models.UserModel;

import java.util.Collection;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private UUID id;

    private String email;

    private String password;

    private boolean isDeleted;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(UserModel user) {
        authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                .toList();
        id = user.getId();
        email = user.getEmail();
        password = user.getPassword();
        isDeleted = user.isDeleted();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isDeleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }
}
