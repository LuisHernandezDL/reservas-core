/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.controlador;

/**
 *
 * @author Luis
 */
import com.proyecto.reservas.core.servicio.PilaAccionesService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acciones")
public class PilaAccionesController {

    @Autowired
    private PilaAccionesService pilaAccionesService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchange = "acciones-exchange";
    private final String routingKey = "acciones.evento";

    @PostMapping
    public String apilarAccion(@RequestBody String accion) {
    pilaAccionesService.registrarAccion(accion); // ✅ CORRECTO
    rabbitTemplate.convertAndSend(exchange, routingKey, "Acción registrada: " + accion);
    return "Acción registrada.";
        }

    @DeleteMapping
    public String desapilarAccion() {
      String accion = pilaAccionesService.deshacerUltimaAccion(); 
        if (accion != null) {
            rabbitTemplate.convertAndSend(exchange, routingKey, "Acción deshecha: " + accion);
          return "Acción deshecha: " + accion;
        } else {
            return "No hay acciones para deshacer.";
          }
}

    @GetMapping("/ultima")
    public String verUltimaAccion() {
        String ultima = pilaAccionesService.verUltimaAccion();
        return (ultima != null) ? ultima : "Pila vacía.";
    }

    @GetMapping("/contar")
    public int contarAcciones() {
        return pilaAccionesService.contarAcciones();
    }
}
