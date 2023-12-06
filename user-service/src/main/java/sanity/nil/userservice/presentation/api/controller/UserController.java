package sanity.nil.userservice.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sanity.nil.userservice.application.dto.query.UserAuthQueryDTO;
import sanity.nil.userservice.application.dto.query.UserQueryDTO;
import sanity.nil.userservice.application.service.UserQueryService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserQueryService userQueryService;

    @GetMapping("/user")
    public ResponseEntity<UserAuthQueryDTO> getByEmailAndPassword(@RequestParam String email,
                                                                  @RequestParam String password) {
        UserQueryDTO userQueryDTO = userQueryService.getUserByEmailAndPasswordQuery.handle(email, password);
        return ResponseEntity
                .status(200)
                .body(new UserAuthQueryDTO(userQueryDTO.userID, userQueryDTO.roles));
    }


}
