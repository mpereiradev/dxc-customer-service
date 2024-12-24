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

  @Value("${dxc.customer.rabbitmq.routing_key}")
  private String routingKey;

  @Value("${dxc.customer.rabbitmq.create.queue}")
  private String customerCreateQueue;

  @Value("${dxc.customer.rabbitmq.update.queue}")
  private String customerUpdateQueue;

  @Value("${dxc.customer.rabbitmq.delete.queue}")
  private String customerDeleteQueue;

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  @Bean
  Binding bindingCustomerCreate(TopicExchange exchange) {
    Queue queue = new Queue(customerCreateQueue, true);
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }

  @Bean
  Binding bindingCustomerUpdate(TopicExchange exchange) {
    Queue queue = new Queue(customerUpdateQueue, true);
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }

  @Bean
  Binding bindingCustomerDelete(TopicExchange exchange) {
    Queue queue = new Queue(customerDeleteQueue, true);
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }
}
