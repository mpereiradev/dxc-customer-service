package dev.matheuspereira.dxc_customer_service.application.data.response;

import dev.matheuspereira.dxc_customer_service.domain.model.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for customer details.")
public class CustomerResponse {

    @Schema(description = "Customer's ID.", example = "1")
    private Long id;

    @Schema(description = "Customer's first name.", example = "John")
    private String firstName;

    @Schema(description = "Customer's last name.", example = "Doe")
    private String lastName;

    @Schema(description = "Customer's document.", example = "86194948051")
    private String document;

    @Schema(description = "Customer's document type.", example = "CPF")
    private DocumentType documentType;

    @Schema(description = "Customer's email address.", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Customer's phone number.", example = "+1234567890")
    private String phoneNumber;
}
