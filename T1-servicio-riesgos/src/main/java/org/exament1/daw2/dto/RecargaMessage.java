package org.exament1.daw2.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RecargaMessage {

    private Long idRecarga;
    private String idTarjeta;
    private BigDecimal saldoDisponible;
    private BigDecimal montoRecarga;
    private LocalDateTime fechaRecarga;
}
