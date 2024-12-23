package dev.matheuspereira.dxc_customer_service.domain.ports.driver;

import dev.matheuspereira.dxc_customer_service.domain.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer createCustomerAsync(Customer customer);
    Customer updateCustomerAsync(Long id, Customer customer);
    Customer partiallyUpdateCustomerAsync(Long id, Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void deleteCustomerAsync(Long id);
}
