package org.exament1.tarjetas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TarjetaRequest {

    @NotBlank(message = "El id de la tarjeta es obligatorio")
    private String idTarjeta;

    @NotBlank(message = "El nombre del titular es obligatorio")
    private String nomTitular;

    @NotNull(message = "El saldo asignado es obligatorio")
    @DecimalMin(value = "0.00",
            message = "El saldo asignado no puede ser negativo")
    private BigDecimal saldoAsignado;

    @NotNull(message = "El saldo disponible es obligatorio")
    @DecimalMin(value = "0.00",
            message = "El saldo disponible no puede ser negativo")
    private BigDecimal saldoDisponible;
}
