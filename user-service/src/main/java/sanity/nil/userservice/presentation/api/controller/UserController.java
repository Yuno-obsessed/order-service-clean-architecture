package sanity.nil.userservice.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.userservice.application.dto.command.CreateUserCommandDTO;
import sanity.nil.userservice.application.dto.query.UserAuthQueryDTO;
import sanity.nil.userservice.application.dto.query.UserQueryDTO;
import sanity.nil.userservice.application.dto.request.UserDTO;
import sanity.nil.userservice.application.dto.request.UserRoleDTO;
import sanity.nil.userservice.application.dto.response.CreatedUserDTO;
import sanity.nil.userservice.application.service.UserCommandService;
import sanity.nil.userservice.application.service.UserQueryService;
import sanity.nil.userservice.domain.user.entity.User;
import sanity.nil.userservice.domain.user.vo.Role;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class UserController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @PostMapping("/get")
    public ResponseEntity<UserAuthQueryDTO> getByEmailAndPassword(@RequestBody UserDTO userDTO) {
        UserQueryDTO userQueryDTO = userQueryService.getUserByEmailAndPasswordQuery.handle(userDTO.email, userDTO.password);
        return ResponseEntity
                .status(200)
                .body(new UserAuthQueryDTO(userQueryDTO.userID, userQueryDTO.roles));
    }

    @PostMapping()
    public ResponseEntity<CreatedUserDTO> createUser(@RequestBody CreateUserCommandDTO createUserCommandDTO) {
        User createdUser = userCommandService.createUserCommand.handle(createUserCommandDTO);
        return ResponseEntity
                .status(201)
                .body(new CreatedUserDTO(createdUser.getID(), createdUser.getRoles().stream().map(Role::getRoleType).toList()));
    }

    @PutMapping("/addRole")
    public ResponseEntity<String> addRole(@RequestBody UserRoleDTO userRoleDTO) {
        return ResponseEntity
                .status(200)
                .body(null);
    }


}
