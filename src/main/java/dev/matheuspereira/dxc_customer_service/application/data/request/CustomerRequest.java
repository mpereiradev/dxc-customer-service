package dev.matheuspereira.dxc_customer_service.application.data.request;

import dev.matheuspereira.dxc_customer_service.domain.model.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Request object for creating or updating a customer.")
public class CustomerRequest {
    @NotBlank
    @Size(max = 50)
    @Schema(description = "Customer's first name.", example = "John", required = true)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "Customer's last name.", example = "Doe", required = true)
    private String lastName;

    @NotBlank
    @Size(max = 14)
    @Schema(description = "Customer's document.", example = "86194948051", required = true)
    private String document;

    @NotBlank
    @Schema(description = "Customer's document type.", example = "CPF", required = true)
    private DocumentType documentType;

    @NotBlank
    @Email
    @Schema(description = "Customer's email address.", example = "john.doe@example.com", required = true)
    private String email;

    @NotBlank
    @Size(max = 15)
    @Schema(description = "Customer's phone number.", example = "+1234567890", required = true)
    private String phoneNumber;
}
