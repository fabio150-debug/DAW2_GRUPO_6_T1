package org.exament1.daw2.messaging;

import lombok.RequiredArgsConstructor;
import org.exament1.daw2.dto.RecargaMessage;
import org.exament1.daw2.service.AnalisisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecargaConsumer {

    private final AnalisisService analisisService;

    @RabbitListener(queues = "${app.queue.name}")
    public void consumir(RecargaMessage mensaje) {

        System.out.println(
                "Recarga recibida: " + mensaje.getIdRecarga()
        );

        analisisService.analizarRecarga(mensaje);
    }
}
