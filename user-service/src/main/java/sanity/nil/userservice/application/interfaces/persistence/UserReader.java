package sanity.nil.userservice.application.interfaces.persistence;

import sanity.nil.userservice.application.dto.query.UserQueryDTO;

import java.util.List;

public interface UserReader {

    List<UserQueryDTO> getUsers();

    UserQueryDTO getUserByID();

    UserQueryDTO getUserByUsername();

    UserQueryDTO getUserByEmail();
}
