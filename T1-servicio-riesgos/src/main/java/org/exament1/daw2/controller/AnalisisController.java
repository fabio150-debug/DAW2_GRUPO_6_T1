package org.exament1.daw2.controller;

import lombok.RequiredArgsConstructor;
import org.exament1.daw2.entity.Analisis;
import org.exament1.daw2.service.AnalisisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analisis")
@RequiredArgsConstructor
public class AnalisisController {

    private final AnalisisService analisisService;

    @GetMapping
    public ResponseEntity<List<Analisis>> listar() {
        return ResponseEntity.ok(analisisService.listarAnalisis());
    }
}
