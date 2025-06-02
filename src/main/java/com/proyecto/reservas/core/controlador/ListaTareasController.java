/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.controlador;

import com.proyecto.reservas.core.servicio.ListaTareasService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author Luis
 */

@RestController
@RequestMapping("/api/tareas")
public class ListaTareasController {
    
    @Autowired
    private ListaTareasService listaTareasService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchange = "tareas-exchange";
    private final String routingKey = "tareas.evento";

    @PostMapping
    public String agregarTarea(@RequestBody String tarea) {
        listaTareasService.agregarTarea(tarea);
        rabbitTemplate.convertAndSend(exchange, routingKey, "Tarea agregada: " + tarea);
        return "Tarea agregada correctamente.";
    }

    @DeleteMapping
    public String eliminarTarea(@RequestBody String tarea) {
        boolean eliminada = listaTareasService.eliminarTarea(tarea);
        if (eliminada) {
            rabbitTemplate.convertAndSend(exchange, routingKey, "Tarea eliminada: " + tarea);
            return "Tarea eliminada correctamente.";
        }
        return "Tarea no encontrada.";
    }

    @GetMapping
    public List<String> listarTareas() {
        return listaTareasService.listarTareas();
    }

    @GetMapping("/contar")
    public int contarTareas() {
        return listaTareasService.contarTareas();
    }
}
