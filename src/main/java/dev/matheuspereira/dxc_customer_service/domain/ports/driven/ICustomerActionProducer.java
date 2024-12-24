package dev.matheuspereira.dxc_customer_service.domain.ports.driven;

import dev.matheuspereira.dxc_customer_service.domain.model.CustomerAction;

public interface ICustomerActionProducer {

  void sendCustomerActionMessage(CustomerAction customerAction);

}
