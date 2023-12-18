package sanity.nil.userservice.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.userservice.application.dto.boundary.UserDTO;
import sanity.nil.userservice.application.dto.query.UserAuthQueryDTO;
import sanity.nil.userservice.application.dto.query.UserQueryDTO;
import sanity.nil.userservice.application.service.UserQueryService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserQueryService userQueryService;

    @PostMapping("/user/get")
    public ResponseEntity<UserAuthQueryDTO> getByEmailAndPassword(@RequestBody UserDTO userDTO) {
        UserQueryDTO userQueryDTO = userQueryService.getUserByEmailAndPasswordQuery.handle(userDTO.email, userDTO.password);
        return ResponseEntity
                .status(200)
                .body(new UserAuthQueryDTO(userQueryDTO.userID, userQueryDTO.roles));
    }


}
