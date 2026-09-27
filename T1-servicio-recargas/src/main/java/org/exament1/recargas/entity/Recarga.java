package org.exament1.recargas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recarga")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recarga")
    private Long idRecarga;

    @Column(name = "id_tarjeta", nullable = false)
    private String idTarjeta;

    @Column(name = "saldo_disponible", nullable = false)
    private BigDecimal saldoDisponible;

    @Column(name = "monto_recarga", nullable = false)
    private BigDecimal montoRecarga;

    @Column(name = "fecha_recarga", nullable = false)
    private LocalDateTime fechaRecarga;
}
