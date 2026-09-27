package org.exament1.recargas.messaging;

import lombok.RequiredArgsConstructor;
import org.exament1.recargas.entity.Recarga;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecargaProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.exchange.name}")
    private String exchangeName;

    @Value("${app.routing.key}")
    private String routingKey;

    public void enviar(Recarga recarga) {

        rabbitTemplate.convertAndSend(
                exchangeName,
                routingKey,
                recarga
        );
    }
}
