package dev.matheuspereira.dxc_customer_service.application.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfig {

  @Value("${dxc.customer.rabbitmq.exchange}")
  private String exchange;

  @Value("${dxc.customer.action.rabbitmq.queue}")
  private String customerActionQueue;

  @Value("${dxc.customer.action.rabbitmq.routing_key}")
  private String customerActionRoutingKey;

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  @Bean
  Binding bindingCustomerAction(TopicExchange exchange) {
    Queue queue = new Queue(customerActionQueue, true);
    return BindingBuilder.bind(queue).to(exchange).with(customerActionRoutingKey);
  }
}
