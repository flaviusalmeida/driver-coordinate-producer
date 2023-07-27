package br.com.drivercoordinate.producer;

import br.com.drivercoordinate.producer.controller.ProducerController;
import br.com.drivercoordinate.producer.dto.DriverInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProducerControllerTest {

    @Value("${events.ex}")
    private String eventsExName;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ProducerController producerController;

    @Test
    public void shouldSendMessage() {
        DriverInfoDto driverInfo = new DriverInfoDto();

        producerController.setEventsExName(eventsExName);

        ResponseEntity<String> response = producerController.send(driverInfo);

        verify(rabbitTemplate).convertAndSend(eq(eventsExName), eq(""), any(Object.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
