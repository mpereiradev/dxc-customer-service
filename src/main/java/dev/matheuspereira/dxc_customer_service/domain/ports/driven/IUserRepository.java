package dev.matheuspereira.dxc_customer_service.domain.ports.driven;

import dev.matheuspereira.dxc_customer_service.domain.model.User;

public interface IUserRepository {
  User save(User user);
}