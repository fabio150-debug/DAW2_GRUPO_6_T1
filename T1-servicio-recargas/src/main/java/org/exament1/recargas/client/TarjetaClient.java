package org.exament1.recargas.client;

import org.exament1.recargas.dto.TarjetaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "tarjetas-service",
        url = "${app.tarjetas.url}"
)
public interface TarjetaClient {

    @GetMapping("/api/tarjetas/{idTarjeta}")
    TarjetaResponse buscarPorId(
            @PathVariable("idTarjeta") String idTarjeta
    );
}
