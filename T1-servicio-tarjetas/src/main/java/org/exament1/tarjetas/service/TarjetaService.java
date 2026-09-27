package org.exament1.tarjetas.service;

import lombok.RequiredArgsConstructor;
import org.exament1.tarjetas.entity.Tarjeta;
import org.exament1.tarjetas.repository.TarjetaRepository;
import org.exament1.tarjetas.dto.TarjetaRequest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.findAll();
    }

    public Optional<Tarjeta> buscarPorId(String idTarjeta) {
        return tarjetaRepository.findById(idTarjeta);
    }

    public Tarjeta registrarTarjeta(TarjetaRequest request) {

        Tarjeta tarjeta = new Tarjeta();

        tarjeta.setIdTarjeta(request.getIdTarjeta());
        tarjeta.setNomTitular(request.getNomTitular());
        tarjeta.setSaldoAsignado(request.getSaldoAsignado());
        tarjeta.setSaldoDisponible(request.getSaldoDisponible());

        return tarjetaRepository.save(tarjeta);
    }

}
