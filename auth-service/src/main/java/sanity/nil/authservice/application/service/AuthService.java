package sanity.nil.authservice.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.authservice.application.command.*;

@RequiredArgsConstructor
public class AuthService {

    public final LoginCommand loginCommand;
    public final RefreshTokenCommand refreshTokenCommand;
    public final AccessCommand accessCommand;
    public final LogoutCommand logoutCommand;
    public final RegisterCommand registerCommand;
}
