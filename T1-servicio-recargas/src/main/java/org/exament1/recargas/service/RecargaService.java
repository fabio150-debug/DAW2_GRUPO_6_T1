package org.exament1.recargas.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.exament1.recargas.client.TarjetaClient;
import org.exament1.recargas.dto.TarjetaResponse;
import org.exament1.recargas.entity.Recarga;
import org.exament1.recargas.messaging.RecargaProducer;
import org.exament1.recargas.repository.RecargaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecargaService {

    private final RecargaRepository recargaRepository;
    private final TarjetaClient tarjetaClient;
    private final RecargaProducer recargaProducer;

    public Recarga registrarRecarga(
            String idTarjeta,
            BigDecimal montoRecarga) {

        TarjetaResponse tarjeta;

        try {
            tarjeta = tarjetaClient.buscarPorId(idTarjeta);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException(
                    "La tarjeta " + idTarjeta + " no existe."
            );
        }

        Recarga recarga = new Recarga();

        recarga.setIdTarjeta(idTarjeta);
        recarga.setSaldoDisponible(tarjeta.getSaldoDisponible());
        recarga.setMontoRecarga(montoRecarga);
        recarga.setFechaRecarga(LocalDateTime.now());

        Recarga guardada = recargaRepository.save(recarga);

        recargaProducer.enviar(guardada);

        return guardada;
    }

    public List<Recarga> listarRecargas() {
        return recargaRepository.findAll();
    }
}
