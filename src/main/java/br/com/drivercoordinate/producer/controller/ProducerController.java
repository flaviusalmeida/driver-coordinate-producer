package br.com.drivercoordinate.producer.controller;

import br.com.drivercoordinate.producer.dto.DriverInfoDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${events.ex}")
    private String eventsExName;

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello Producer!");
    }

    @PostMapping(path = "/send")
    public ResponseEntity<String> send(@RequestBody DriverInfoDto dto) {
        rabbitTemplate.convertAndSend(eventsExName,"", dto);
        return ResponseEntity.ok("Message send!");
    }



}
