package dev.matheuspereira.dxc_customer_service.application.web;

import dev.matheuspereira.dxc_customer_service.application.data.request.AuthRequest;
import dev.matheuspereira.dxc_customer_service.application.data.request.UserRegisterRequest;
import dev.matheuspereira.dxc_customer_service.application.data.response.JwtTokenResponse;
import dev.matheuspereira.dxc_customer_service.domain.model.AuthCredentials;
import dev.matheuspereira.dxc_customer_service.domain.model.User;
import dev.matheuspereira.dxc_customer_service.domain.ports.driven.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "The Auth API")
public class AuthController {
    private final IAuthService authService;
    private final ModelMapper modelMapper;

  @Operation(
      summary = "Authentication user",
      description =
          "Receives a username and password then validates the credentials and returns the token")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successful and token generated"),
        @ApiResponse(responseCode = "401", description = "Error, invalid credentials"),
        @ApiResponse(responseCode = "500", description = "Error, internal error")
      })
  @PostMapping("/singIn")
  public ResponseEntity<JwtTokenResponse> singIn(@RequestBody AuthRequest authRequest) {
        AuthCredentials credentials = modelMapper.map(authRequest, AuthCredentials.class);
        var jwtResponse = authService.auth(credentials);
        return ResponseEntity.ok(modelMapper.map(jwtResponse, JwtTokenResponse.class));
    }

    @Operation(
            summary = "Register user",
            description = "Receives a user data and register new user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful and user registered"),
            @ApiResponse(responseCode = "401", description = "Error, invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Error, internal error")
    })
    @PostMapping("/singUp")
    public ResponseEntity<String> singUp(@RequestBody UserRegisterRequest userRegisterRequest) {
        var user = modelMapper.map(userRegisterRequest, User.class);
        user = authService.register(user);
        return ResponseEntity.ok(user.getFullName() + " registered with successful");
    }
}