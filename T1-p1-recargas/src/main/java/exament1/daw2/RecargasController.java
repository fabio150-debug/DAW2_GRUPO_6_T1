package exament1.daw2;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/recargas")
public class RecargasController {

    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate; // Inyección de RabbitMQ

    // Constructor actualizado con RabbitTemplate
    public RecargasController(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    @CircuitBreaker(name = "t1-p1-tarjetas", fallbackMethod = "fallbackRecarga")
    @SuppressWarnings("unchecked")
    public Map<String, Object> registrarRecarga(@RequestBody Map<String, Object> request) {
        String idTarjeta = request.get("id_tarjeta").toString();
        double montoRecarga = Double.parseDouble(request.get("monto_recarga").toString());

        // Comunicación sincrónica con ms-tarjetas usando Eureka
        String url = "http://t1-p1-tarjetas/api/tarjetas/" + idTarjeta;
        Map<String, Object> respuestaTarjeta = restTemplate.getForObject(url, Map.class);

        if (respuestaTarjeta == null || respuestaTarjeta.isEmpty()) {
            return Map.of(
                    "estado", "Error",
                    "mensaje", "La tarjeta con ID " + idTarjeta + " no existe."
            );
        }

        // Obtener el saldo disponible desde el servicio de Tarjetas
        double saldoDisponible = ((Number) respuestaTarjeta.get("saldo_disponible")).doubleValue();

        // Estructura de la recarga registrada
        Map<String, Object> recargaRegistrada = Map.of(
                "id_recarga", System.currentTimeMillis(), // Generado automáticamente
                "id_tarjeta", idTarjeta,
                "saldo_disponible", saldoDisponible,
                "monto_recarga", montoRecarga,
                "fecha_recarga", new Date()               // Fecha actual del sistema
        );

        // Envío asíncrono del mensaje a la cola del área de Riesgo
        rabbitTemplate.convertAndSend("grupo6_Queue", recargaRegistrada);

        return recargaRegistrada;
    }

    @SuppressWarnings("unused")
    public Map<String, Object> fallbackRecarga(Map<String, Object> request, Exception e) {
        return Map.of(
                "estado", "Error",
                "motivo", "El servicio de Tarjetas no está disponible, intente más tarde."
        );
    }
}