package exament1.daw2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    // Simulando una base de datos en memoria con todos los campos requeridos
    private static final List<Map<String, Object>> TARJETAS = List.of(
            Map.of("id_tarjeta", "1", "nom_titular", "Juan Perez", "saldo_asignado", 500.0, "saldo_disponible", 350.0),
            Map.of("id_tarjeta", "2", "nom_titular", "Maria Gomez", "saldo_asignado", 1000.0, "saldo_disponible", 120.5),
            Map.of("id_tarjeta", "3", "nom_titular", "Carlos Ruiz", "saldo_asignado", 200.0, "saldo_disponible", 0.0)
    );

    @Value("${server.port}")
    private String puerto;

    // 1. Consultar el listado de las tarjetas
    @GetMapping
    public List<Map<String, Object>> listarTarjetas() {
        return TARJETAS;
    }

    // 2. Consultar una tarjeta por su id
    @GetMapping("/{id}")
    public Map<String, Object> consultarTarjetaPorId(@PathVariable String id) {
        Map<String, Object> tarjetaEncontrada = TARJETAS.stream()
                .filter(t -> t.get("id_tarjeta").equals(id))
                .findFirst()
                .orElse(null);

        if (tarjetaEncontrada != null) {
            // Opcional: añadimos la instancia para que veas el balanceo de carga del profe
            // (puedes crear un nuevo mapa mutable o retornarlo directamente)
            return tarjetaEncontrada;
        }
        return null;
    }
}
