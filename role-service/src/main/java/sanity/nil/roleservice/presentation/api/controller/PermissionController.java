package sanity.nil.roleservice.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sanity.nil.roleservice.application.service.PermissionQueryService;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
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
