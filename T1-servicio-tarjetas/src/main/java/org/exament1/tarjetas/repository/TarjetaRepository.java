package org.exament1.tarjetas.repository;


import org.exament1.tarjetas.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {
}
