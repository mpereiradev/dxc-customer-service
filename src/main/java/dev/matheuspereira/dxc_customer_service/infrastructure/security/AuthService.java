package dev.matheuspereira.dxc_customer_service.infrastructure.security;

import dev.matheuspereira.dxc_customer_service.domain.exception.BusinessException;
import dev.matheuspereira.dxc_customer_service.domain.model.AuthCredentials;
import dev.matheuspereira.dxc_customer_service.domain.model.JwtToken;
import dev.matheuspereira.dxc_customer_service.domain.model.User;
import dev.matheuspereira.dxc_customer_service.domain.model.UserRole;
import dev.matheuspereira.dxc_customer_service.domain.ports.driven.IAuthService;
import dev.matheuspereira.dxc_customer_service.domain.ports.driven.IUserRepository;
import dev.matheuspereira.dxc_customer_service.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
  private final IUserRepository userRepository;

  private final ModelMapper modelMapper;
  private final JwtService jwtService;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  @Override
  public User register(User user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole(UserRole.USER);

      var entity = userRepository.save(user);
      return modelMapper.map(entity, User.class);
    } catch (Exception e) {
      log.error("User not registered", e);
      throw new BusinessException("Could not register the user, there was an internal error", 500);
    }
  }

  @Override
  public JwtToken auth(AuthCredentials credentials) {
    var authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPassword()));

    if (authentication.isAuthenticated()) {
      String token = jwtService.generateToken((UserEntity) authentication.getPrincipal());
      return JwtToken.builder()
          .accessToken(token)
          .expiration(jwtService.extractExpiration(token))
          .build();
    }

    throw new BusinessException("Invalid credentials", 401);
  }
}
