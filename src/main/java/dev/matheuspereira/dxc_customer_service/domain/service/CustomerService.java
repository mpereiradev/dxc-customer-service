package dev.matheuspereira.dxc_customer_service.domain.service;

import dev.matheuspereira.dxc_customer_service.domain.model.Customer;
import dev.matheuspereira.dxc_customer_service.domain.ports.driver.ICustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

  @Override
  public Customer createCustomerAsync(Customer customer) {
    return null;
  }

  @Override
  public Customer updateCustomerAsync(Long id, Customer customer) {
    return null;
  }

  @Override
  public Customer partiallyUpdateCustomerAsync(Long id, Customer customer) {
    return null;
  }

  @Override
  public List<Customer> getAllCustomers() {
    return List.of();
  }

  @Override
  public Customer getCustomerById(Long id) {
    return null;
  }

  @Override
  public void deleteCustomerAsync(Long id) {}
}
