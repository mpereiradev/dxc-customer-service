package dev.matheuspereira.dxc_customer_service.application.data.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenResponse {
  private String accessToken;
  private Date expiration;
}
