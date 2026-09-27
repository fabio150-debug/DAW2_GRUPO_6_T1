package org.exament1.daw2.service;

import lombok.RequiredArgsConstructor;
import org.exament1.daw2.dto.RecargaMessage;
import org.exament1.daw2.entity.Analisis;
import org.exament1.daw2.repository.AnalisisRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalisisService {

    private final AnalisisRepository analisisRepository;

    public void analizarRecarga(RecargaMessage mensaje) {

        BigDecimal limite = mensaje.getSaldoDisponible()
                .multiply(new BigDecimal("0.70"));

        String situacion;

        if (mensaje.getMontoRecarga().compareTo(limite) <= 0) {
            situacion = "Aprobada";
        } else {
            situacion = "Observada";
        }

        Analisis analisis = new Analisis();

        analisis.setIdRecarga(mensaje.getIdRecarga());
        analisis.setIdTarjeta(mensaje.getIdTarjeta());
        analisis.setSaldoDisponible(mensaje.getSaldoDisponible());
        analisis.setMontoRecarga(mensaje.getMontoRecarga());
        analisis.setFechaRecarga(mensaje.getFechaRecarga());
        analisis.setSituacion(situacion);

        analisisRepository.save(analisis);
    }

    public List<Analisis> listarAnalisis() {
        return analisisRepository.findAll();
    }
}
