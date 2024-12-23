package dev.matheuspereira.dxc_customer_service.infrastructure.security;

import dev.matheuspereira.dxc_customer_service.infrastructure.entity.UserEntity;
import dev.matheuspereira.dxc_customer_service.infrastructure.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}