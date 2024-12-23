package dev.matheuspereira.dxc_customer_service.domain.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
  private String accessToken;
  private Date expiration;
}
