/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.controlador;

/**
 *
 * @author Luis
 */
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.proyecto.reservas.core.servicio.ColaTareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cola")
public class ColaTareasController {

      @Autowired
    private ColaTareasService colaTareasService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchange = "cola-exchange";
    private final String routingKey = "cola.evento";

    @PostMapping
    public String agregarTarea(@RequestBody String tarea) {
        colaTareasService.agregarTarea(tarea);
        rabbitTemplate.convertAndSend(exchange, routingKey, "Tarea encolada: " + tarea);
        return "Tarea encolada correctamente.";
    }

    @DeleteMapping
    public String procesarTarea() {
        String tarea = colaTareasService.procesarTarea();
        rabbitTemplate.convertAndSend(exchange, routingKey, "Tarea procesada: " + tarea);
        return "Tarea procesada: " + tarea;
    }

    @GetMapping("/proxima")
    public String verProximaTarea() {
        return colaTareasService.verProximaTarea();
    }

    @GetMapping("/contar")
    public int contarTareas() {
        return colaTareasService.contarTareas();
    }
}