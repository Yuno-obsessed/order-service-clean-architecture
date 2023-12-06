package sanity.nil.userservice.infrastructure.database.orm.mapper;

import sanity.nil.userservice.application.consts.RoleType;
import sanity.nil.userservice.application.dto.query.UserQueryDTO;
import sanity.nil.userservice.domain.user.entity.User;
import sanity.nil.userservice.domain.user.vo.Role;
import sanity.nil.userservice.domain.user.vo.UserID;
import sanity.nil.userservice.infrastructure.database.models.RoleModel;
import sanity.nil.userservice.infrastructure.database.models.UserModel;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserQueryDTO convertUserModelToQueryDTO(UserModel model) {
        return new UserQueryDTO(model.getId().toString(), model.getFirstName(), model.getLastName(),
                model.getUsername(), model.getEmail(), model.getPassword(), model.isEmailConfirmed(),
                model.getRoles().stream().map(e -> e.getRoleType().toString()).toList());
    }

    public static User convertUserModelToEntity(UserModel model) {
        return new User(new UserID(model.getId()), model.getRoles().stream().map(UserMapper::convertRoleModelToEntity).collect(Collectors.toSet()),
                model.getFirstName(), model.getLastName(), model.getUsername(),
                model.getEmail(), model.isEmailConfirmed(), model.getPassword());
    }

    public static UserModel convertUserEntityToModel(User entity) {
        return new UserModel(entity.getUserID().getID(), entity.getFirstName(), entity.getLastName(), entity.getUsername(),
                entity.getEmail(), entity.isEmailConfirmed(), entity.getPassword(),
                entity.getRoles().stream().map(UserMapper::convertRoleEntityToModel).collect(Collectors.toSet()));
    }

    public static Role convertRoleModelToEntity(RoleModel model) {
        return new Role(model.getRoleType().toString(), model.getCreatedAt());
    }

    public static RoleModel convertRoleEntityToModel(Role entity) {
        return new RoleModel(RoleType.valueOf(entity.getRoleType()), entity.getCreatedAt());
    }
}
