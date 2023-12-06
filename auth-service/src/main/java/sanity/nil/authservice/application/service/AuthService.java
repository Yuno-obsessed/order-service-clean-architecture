package sanity.nil.authservice.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.authservice.application.command.AccessCommand;
import sanity.nil.authservice.application.command.LoginCommand;
import sanity.nil.authservice.application.command.LogoutCommand;
import sanity.nil.authservice.application.command.RefreshTokenCommand;

@RequiredArgsConstructor
public class AuthService {

    public final LoginCommand loginCommand;
    public final RefreshTokenCommand refreshTokenCommand;
    public final AccessCommand accessCommand;
    public final LogoutCommand logoutCommand;
}
