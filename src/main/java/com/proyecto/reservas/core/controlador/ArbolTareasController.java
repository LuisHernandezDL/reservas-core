/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.controlador;

/**
 *
 * @author Luis
 */
import com.proyecto.reservas.core.servicio.ArbolTareasService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/arbol")
public class ArbolTareasController {

    @Autowired
    private ArbolTareasService arbolTareasService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchange = "arbol-exchange";
    private final String routingKey = "arbol.evento";

    @PostMapping("/inicializar")
    public String inicializarArbol(@RequestBody String tareaRaiz) {
        arbolTareasService.inicializarArbol(tareaRaiz);
        rabbitTemplate.convertAndSend(exchange, routingKey, "Árbol inicializado con raíz: " + tareaRaiz);
        return "Árbol inicializado con éxito.";
    }

    @PostMapping("/agregar")
    public String agregarTarea(@RequestParam String padre, @RequestParam String tarea) {
        boolean agregado = arbolTareasService.agregarTarea(padre, tarea);
        if (agregado) {
            rabbitTemplate.convertAndSend(exchange, routingKey, "Tarea agregada: '" + tarea + "' como hija de '" + padre + "'");
            return "Tarea agregada correctamente.";
        } else {
            return "No se encontró el nodo padre.";
        }
    }

    @GetMapping("/mostrar")
    public String mostrarJerarquia() {
        return arbolTareasService.imprimirArbol();
    }
}