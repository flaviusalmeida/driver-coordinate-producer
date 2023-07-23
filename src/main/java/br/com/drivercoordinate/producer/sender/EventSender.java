package br.com.drivercoordinate.producer.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventSender {

    private final RabbitTemplate rabbitTemplate;
    private final AmqpTemplate queueSender;
    private final Queue queue;

    public EventSender(RabbitTemplate rabbitTemplate, AmqpTemplate queueSender, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueSender = queueSender;
        this.queue = queue;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }

    public void sendExchange(String message) {
        queueSender.convertAndSend(queue.getName(), "", message);
    }
}
