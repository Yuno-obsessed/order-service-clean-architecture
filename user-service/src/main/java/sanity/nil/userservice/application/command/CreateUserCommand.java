package sanity.nil.userservice.application.command;

import lombok.RequiredArgsConstructor;
import sanity.nil.userservice.application.consts.RoleType;
import sanity.nil.userservice.application.dto.command.CreateUserCommandDTO;
import sanity.nil.userservice.application.exceptions.PasswordsDoNotMatch;
import sanity.nil.userservice.application.interfaces.persistence.RoleReader;
import sanity.nil.userservice.application.interfaces.persistence.UserDAO;
import sanity.nil.userservice.domain.user.entity.User;
import sanity.nil.userservice.domain.user.services.UserService;
import sanity.nil.userservice.domain.user.vo.Role;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class CreateUserCommand {

    private final UserService userService;
    private final UserDAO userDAO;
    private final RoleReader roleReader;

    public User handle(CreateUserCommandDTO createUserCommandDTO) {
        if (!createUserCommandDTO.password.equals(createUserCommandDTO.repeatPassword)) {
            throw new PasswordsDoNotMatch();
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleReader.getRoleByType(RoleType.ROLE_USER));
        User createdUser = userService.create(roles, createUserCommandDTO.firstName,
                createUserCommandDTO.lastName, createUserCommandDTO.username,
                createUserCommandDTO.email, createUserCommandDTO.password);
        return userDAO.createUser(createdUser);
    }
}
