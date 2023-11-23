package sanity.nil.userservice.infrastructure.database.impl;

import sanity.nil.userservice.application.dto.query.UserQueryDTO;
import sanity.nil.userservice.application.interfaces.persistence.UserDAO;
import sanity.nil.userservice.application.interfaces.persistence.UserReader;
import sanity.nil.userservice.domain.entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO, UserReader {

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
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
    public UserQueryDTO getUserByID() {
        return null;
    }

    @Override
    public UserQueryDTO getUserByUsername() {
        return null;
    }

    @Override
    public UserQueryDTO getUserByEmail() {
        return null;
    }
}
