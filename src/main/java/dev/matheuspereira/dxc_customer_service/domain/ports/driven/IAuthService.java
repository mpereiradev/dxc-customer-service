package dev.matheuspereira.dxc_customer_service.domain.ports.driven;

import dev.matheuspereira.dxc_customer_service.domain.model.AuthCredentials;
import dev.matheuspereira.dxc_customer_service.domain.model.JwtToken;
import dev.matheuspereira.dxc_customer_service.domain.model.User;

public interface IAuthService {
    User register(User user);
    JwtToken auth(AuthCredentials credentials);
}