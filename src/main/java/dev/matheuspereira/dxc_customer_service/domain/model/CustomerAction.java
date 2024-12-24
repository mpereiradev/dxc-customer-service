package dev.matheuspereira.dxc_customer_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAction {
    private ActionType actionType;
    private Customer customer;
}
