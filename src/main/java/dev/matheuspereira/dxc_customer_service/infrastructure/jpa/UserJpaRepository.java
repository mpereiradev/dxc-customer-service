package dev.matheuspereira.dxc_customer_service.infrastructure.jpa;

import dev.matheuspereira.dxc_customer_service.infrastructure.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}