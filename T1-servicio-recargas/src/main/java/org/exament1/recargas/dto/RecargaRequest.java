package org.exament1.recargas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecargaRequest {
    //Peticiones obligatorias
    @NotBlank(message = "El id de la tarjeta de recarga es obligatorio")
    private String idTarjeta;

    @NotNull(message = "El monto de la tarjeta de recarga es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto de recarga debe ser mayor a 0")
    private BigDecimal montoRecarga;
}
