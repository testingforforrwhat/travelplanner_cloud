package com.test.travelplanner.controller;


import com.test.travelplanner.model.dto.unifiedGlobalResponse.ApiResponse;
import com.test.travelplanner.model.dto.user.LoginAuthResponse;
import com.test.travelplanner.model.dto.user.PermissionResponse;
import com.test.travelplanner.model.entity.user.UserEntity;
import com.test.travelplanner.service.impl.AuthenticationService;
import com.test.travelplanner.model.LoginRequest;
import com.test.travelplanner.model.RegisterRequest;
import com.test.travelplanner.service.impl.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {


   private final AuthenticationService authenticationService;
    private final PermissionService permissionService;


    public AuthenticationController(AuthenticationService authenticationService, PermissionService permissionService) {
       this.authenticationService = authenticationService;
        this.permissionService = permissionService;
    }


   @PostMapping("/register")
   @ResponseStatus(HttpStatus.CREATED)
   @Operation(summary = "register")
   public UserEntity register(@RequestBody RegisterRequest body) {
       return authenticationService.register(
               body.username(),
               body.password(),
               body.role(),
               body.email());
   }


   @PostMapping("/login")
   public ResponseEntity<ApiResponse<LoginAuthResponse>> login(@RequestBody LoginRequest body) {
       LoginAuthResponse authResponse = authenticationService.login(body.username(), body.password());
       return ResponseEntity.status(HttpStatus.OK)
               .body(ApiResponse.success(authResponse));
   }

    @GetMapping("/permissions")
    public ResponseEntity<PermissionResponse> getPermissions(
            @AuthenticationPrincipal UserEntity currentUser) {

        PermissionResponse response = new PermissionResponse();
        response.setMenus(permissionService.getUserMenus(currentUser.getId()));
        response.setOperates(permissionService.getUserOperates(currentUser.getId()));

        return ResponseEntity.ok(response);
    }

}
