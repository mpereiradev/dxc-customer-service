package dev.matheuspereira.dxc_customer_service.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.matheuspereira.dxc_customer_service.domain.model.CustomerAction;
import dev.matheuspereira.dxc_customer_service.domain.ports.driven.ICustomerActionProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerActionProducer implements ICustomerActionProducer {
  private final RabbitTemplate rabbitTemplate;
  private final ObjectMapper objectMapper;

  @Value("${dxc.customer.rabbitmq.exchange}")
  private String exchange;

  @Value("${dxc.customer.action.rabbitmq.routing_key}")
  private String customerActionRoutingKey;

  @Override
  public void sendCustomerActionMessage(CustomerAction customerAction) {
    try {
      String message = objectMapper.writeValueAsString(customerAction);
      rabbitTemplate.convertAndSend(exchange, customerActionRoutingKey, message);
      log.info("Sent customer action message: {}", message);
    } catch (JsonProcessingException e) {
      log.error("Unable to convert customer action to json", e);
    } catch (Exception e) {
      log.error("Error sending customer action message.", e);
    }
  }
}
