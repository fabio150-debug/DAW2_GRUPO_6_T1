package org.exament1.recargas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.exament1.recargas.dto.RecargaRequest;
import org.exament1.recargas.entity.Recarga;
import org.exament1.recargas.service.RecargaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/recargas")
@RequiredArgsConstructor
public class RecargaController {

    private final RecargaService recargaService;

    @PostMapping
    public ResponseEntity<?> registrar(
            @Valid @RequestBody RecargaRequest request) {

        try {

            Recarga recarga = recargaService.registrarRecarga(
                    request.getIdTarjeta(),
                    request.getMontoRecarga()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(recarga);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping
    public ResponseEntity<List<Recarga>> listar() {
        return ResponseEntity.ok(
                recargaService.listarRecargas()
        );
    }
}
