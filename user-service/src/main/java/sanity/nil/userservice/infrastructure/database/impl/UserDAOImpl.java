package sanity.nil.userservice.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.userservice.application.dto.query.UserQueryDTO;
import sanity.nil.userservice.application.exceptions.UserNotFoundException;
import sanity.nil.userservice.application.interfaces.persistence.UserDAO;
import sanity.nil.userservice.application.interfaces.persistence.UserReader;
import sanity.nil.userservice.domain.user.entity.User;
import sanity.nil.userservice.infrastructure.database.models.UserModel;
import sanity.nil.userservice.infrastructure.database.orm.UserORM;
import sanity.nil.userservice.infrastructure.database.orm.mapper.UserMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO, UserReader {

    private final UserORM userORM;

    @Override
    public User createUser(User user) {
        UserModel createdUser = userORM.save(UserMapper.convertUserEntityToModel(user));
        return UserMapper.convertUserModelToEntity(createdUser);
    }

    @Override
    public User updateUser(User user) {
        UserModel maybeUser = userORM.findById(user.getUserID().getID()).orElseThrow(
                UserNotFoundException::new);
        UserModel updatedUser = userORM.save(UserMapper.convertUserEntityToModel(user));
        return UserMapper.convertUserModelToEntity(updatedUser);
    }

    @Override
    public User deleteUser(User user) {
        return null;
    }

    @Override
    public List<UserQueryDTO> getUsers() {
        return null;
    }

    @Override
    public UserQueryDTO getUserByID(UUID id) {
        UserModel maybeUser = userORM.findByID(id)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.convertUserModelToQueryDTO(maybeUser);
    }

    @Override
    public UserQueryDTO getUserByUsername(String username) {
        UserModel maybeUser = userORM.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.convertUserModelToQueryDTO(maybeUser);
    }

    @Override
    public UserQueryDTO getUserByEmail(String email) {
        UserModel maybeUser = userORM.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.convertUserModelToQueryDTO(maybeUser);
    }
}
