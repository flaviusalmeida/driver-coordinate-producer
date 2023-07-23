package br.com.drivercoordinate.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageTestDTO {

    private int id = 1;
    private String message = "Test message RabbitMQ";

}
