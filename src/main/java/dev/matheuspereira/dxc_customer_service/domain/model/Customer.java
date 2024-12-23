package dev.matheuspereira.dxc_customer_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private DocumentType documentType;
    private String email;
    private String phoneNumber;
}
