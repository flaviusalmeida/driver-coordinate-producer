package br.com.drivercoordinate.producer.controller;

import br.com.drivercoordinate.producer.sender.EventSender;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final EventSender sender;

    public ProducerController(EventSender sender) {
        this.sender = sender;
    }

    @GetMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello Producer!");
    }

    @GetMapping(path = "/send-msg-test")
    public ResponseEntity<String> sendMessageTest() {
        sender.send("Test message RabbitMQ");
        sender.sendExchange("Test message RabbitMQ whit exchange");
        return ResponseEntity.ok("Message send!");
    }

}
