package org.exament1.recargas.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TarjetaResponse {

    private String idTarjeta;
    private String nomTitular;
    private BigDecimal saldoAsignado;
    private BigDecimal saldoDisponible;
}
