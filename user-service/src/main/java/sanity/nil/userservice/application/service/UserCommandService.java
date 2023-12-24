package sanity.nil.userservice.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.userservice.application.command.CreateUserCommand;

@RequiredArgsConstructor
public class UserCommandService {

    public final CreateUserCommand createUserCommand;
}
