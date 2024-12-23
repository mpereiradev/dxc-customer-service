package dev.matheuspereira.dxc_customer_service.application.web;

import dev.matheuspereira.dxc_customer_service.application.data.request.CustomerRequest;
import dev.matheuspereira.dxc_customer_service.application.data.response.CustomerResponse;
import dev.matheuspereira.dxc_customer_service.application.data.request.PartialCustomerRequest;
import dev.matheuspereira.dxc_customer_service.domain.model.Customer;
import dev.matheuspereira.dxc_customer_service.domain.ports.driver.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer", description = "The Customer API")
public class CustomerController {
  private final ICustomerService customerService;
  private final ModelMapper modelMapper;

  @Operation(
      summary = "Create a new customer",
      description = "This endpoint creates a new customer with the provided details.",
      responses = {
        @ApiResponse(description = "Customer created successfully.", responseCode = "201"),
        @ApiResponse(description = "Invalid request.", responseCode = "400")
      })
  @PostMapping
  public ResponseEntity<CustomerResponse> createCustomer(
      @RequestBody CustomerRequest customerRequest) {
    Customer customer =
        customerService.createCustomerAsync(modelMapper.map(customerRequest, Customer.class));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(modelMapper.map(customer, CustomerResponse.class));
  }

  @Operation(
      summary = "Update a customer",
      description = "This endpoint updates an existing customer by ID.",
      responses = {
        @ApiResponse(description = "Customer updated successfully.", responseCode = "200"),
        @ApiResponse(description = "Customer not found.", responseCode = "404")
      })
  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponse> updateCustomer(
      @PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
    Customer updatedCustomer =
        customerService.updateCustomerAsync(id, modelMapper.map(customerRequest, Customer.class));
    return ResponseEntity.ok(modelMapper.map(updatedCustomer, CustomerResponse.class));
  }

  @Operation(
      summary = "Partially update a customer",
      description = "This endpoint partially updates an existing customer by ID.",
      responses = {
        @ApiResponse(description = "Customer updated successfully.", responseCode = "200"),
        @ApiResponse(description = "Customer not found.", responseCode = "404")
      })
  @PatchMapping("/{id}")
  public ResponseEntity<CustomerResponse> partiallyUpdateCustomer(
      @PathVariable Long id, @RequestBody PartialCustomerRequest partialCustomerRequest) {
    Customer partiallyUpdatedCustomer =
        customerService.partiallyUpdateCustomerAsync(
            id, modelMapper.map(partialCustomerRequest, Customer.class));
    return ResponseEntity.ok(modelMapper.map(partiallyUpdatedCustomer, CustomerResponse.class));
  }

  @Operation(
      summary = "Get a list of all customers",
      description = "This endpoint retrieves a list of all registered customers.",
      responses = {
        @ApiResponse(
            description = "List of customers retrieved successfully.",
            responseCode = "200")
      })
  @GetMapping
  public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    List<Customer> customers = customerService.getAllCustomers();
    List<CustomerResponse> response =
        customers.stream()
            .map(customer -> modelMapper.map(customer, CustomerResponse.class))
            .toList();
    return ResponseEntity.ok(response);
  }

  @Operation(
      summary = "Get a customer by ID",
      description = "This endpoint retrieves details of a customer by their ID.",
      responses = {
        @ApiResponse(description = "Customer found.", responseCode = "200"),
        @ApiResponse(description = "Customer not found.", responseCode = "404")
      })
  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
    Customer customer = customerService.getCustomerById(id);
    return ResponseEntity.ok(modelMapper.map(customer, CustomerResponse.class));
  }

  @Operation(
      summary = "Delete a customer",
      description = "This endpoint deletes a customer by ID.",
      responses = {
        @ApiResponse(description = "Customer deleted successfully.", responseCode = "204"),
        @ApiResponse(description = "Customer not found.", responseCode = "404")
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomerAsync(id);
    return ResponseEntity.noContent().build();
  }
}
