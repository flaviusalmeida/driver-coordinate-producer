package br.com.drivercoordinate.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    @Value("${queue.name}")
    private String name;

    @Bean
    public Queue queue() {
        return new Queue(name, true);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("events.ex");
    }

    @Bean
    Binding Binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


}