package br.com.drivercoordinate.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    @Value("${events.queue}")
    private String eventsQueueName;

    @Value("${events.ex}")
    private String eventsExName;

    @Bean
    public Queue eventsQueue() {
        return new Queue(eventsQueueName, true);
    }

    @Bean
    FanoutExchange eventsExchange() {
        return new FanoutExchange(eventsExName);
    }

    @Bean
    Binding bindEvents() {
        return BindingBuilder.bind(eventsQueue()).to(eventsExchange());
    }

}