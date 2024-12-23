package dev.matheuspereira.dxc_customer_service.infrastructure.repository;

import dev.matheuspereira.dxc_customer_service.domain.model.User;
import dev.matheuspereira.dxc_customer_service.domain.ports.driven.IUserRepository;
import dev.matheuspereira.dxc_customer_service.infrastructure.entity.UserEntity;
import dev.matheuspereira.dxc_customer_service.infrastructure.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
  private final UserJpaRepository userJpaRepository;
  private final ModelMapper modelMapper;

  @Override
  public User save(User user) {
    UserEntity userEntity = userJpaRepository.save(modelMapper.map(user, UserEntity.class));
    return modelMapper.map(userEntity, User.class);
  }
}
