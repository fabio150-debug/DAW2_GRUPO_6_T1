package org.exament1.tarjetas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.exament1.tarjetas.dto.TarjetaRequest;
import org.exament1.tarjetas.entity.Tarjeta;
import org.exament1.tarjetas.service.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
@RequiredArgsConstructor
public class TarjetaController {

    private final TarjetaService tarjetaService;

    @GetMapping
    public ResponseEntity<List<Tarjeta>> listar() {
        return ResponseEntity.ok(tarjetaService.listarTarjetas());
    }

    @GetMapping("/{idTarjeta}")
    public ResponseEntity<Tarjeta> buscarPorId(
            @PathVariable String idTarjeta) {

        return tarjetaService.buscarPorId(idTarjeta)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tarjeta> registrar(
            @Valid @RequestBody TarjetaRequest request) {

        Tarjeta guardada = tarjetaService.registrarTarjeta(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardada);
    }
}

