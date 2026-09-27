package exament1.daw2.riesgo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/riesgos")
public class RiesgoController {


    private static final List<Map<String, Object>> TABLA_ANALISIS = new ArrayList<>();


    @RabbitListener(queues = "Atuncar_Queue")
    public void consumirMensaje(Map<String, Object> recarga) {
        Map<String, Object> analisis = new HashMap<>(recarga);

        double saldoDisponible = Double.parseDouble(recarga.get("saldo_disponible").toString());
        double montoRecarga = Double.parseDouble(recarga.get("monto_recarga").toString());


        if (montoRecarga <= (saldoDisponible * 0.70)) {
            analisis.put("situacion", "Aprobada");
        } else {
            analisis.put("situacion", "Observada");
        }

        TABLA_ANALISIS.add(analisis);
        System.out.println("Solicitud evaluada por Riesgo: " + analisis);
    }


    @GetMapping
    public List<Map<String, Object>> listarAnalisis() {
        return TABLA_ANALISIS;
    }
}