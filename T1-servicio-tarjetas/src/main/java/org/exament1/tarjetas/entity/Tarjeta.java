package org.exament1.tarjetas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tarjeta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta {

    @Id
    @Column(name = "id_tarjeta")
    private String idTarjeta;

    @Column(name = "nom_titular")
    private String nomTitular;

    @Column(name = "saldo_asignado")
    private BigDecimal saldoAsignado;

    @Column(name = "saldo_disponible")
    private BigDecimal saldoDisponible;
}
