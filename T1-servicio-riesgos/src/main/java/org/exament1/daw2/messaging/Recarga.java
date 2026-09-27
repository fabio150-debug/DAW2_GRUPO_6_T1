package org.exament1.daw2.messaging;

import lombok.RequiredArgsConstructor;
import org.exament1.daw2.dto.RecargaDto;
import org.exament1.daw2.entity.Analisis;
import org.exament1.daw2.repository.AnalisisRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class Recarga {

    private final AnalisisRepository analisisRepository;

    @RabbitListener(queues = "${app.queue.name}")
    public void recibirRecarga(RecargaDto mensaje) {
        BigDecimal limite = mensaje.getSaldoDisponible().multiply(BigDecimal.valueOf(0.70));
        String situacion = mensaje.getMontoRecarga().compareTo(limite) > 0 ? "Observada" : "Aprobada";

        Analisis analisis = new Analisis();
        analisis.setIdRecarga(mensaje.getIdRecarga());
        analisis.setIdTarjeta(mensaje.getIdTarjeta());
        analisis.setSaldoDisponible(mensaje.getSaldoDisponible());
        analisis.setMontoRecarga(mensaje.getMontoRecarga());
        analisis.setFechaRecarga(mensaje.getFechaRecarga());
        analisis.setSituacion(situacion);

        analisisRepository.save(analisis);
    }
}