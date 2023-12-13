package sanity.nil.roleservice.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.roleservice.application.service.PermissionQueryService;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PermissionController {

    private final PermissionQueryService permissionQueryService;

    @GetMapping("/permission")
    public ResponseEntity<Boolean> hasPermission(@RequestParam String port,
                                                 @RequestParam String uri,
                                                 @RequestParam String roles) {
        return ResponseEntity
                .status(200)
                .body(permissionQueryService.getPermissionQuery.handle(port, uri, roles));
    }
}
