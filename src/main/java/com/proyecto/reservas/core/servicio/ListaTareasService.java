/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.servicio;

import com.mycompany.reservas.estructuras.lista.ListaPersonalizada;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ListaTareasService {
    

    private ListaPersonalizada<String> tareas = new ListaPersonalizada<>();
    
    @Autowired
    private HistorialService historialService;


    public void agregarTarea(String tarea) {
        tareas.agregar(tarea);
        historialService.registrarAccion("TAREA_CREADA: " + tarea, null); // por ahora null
    }

    public boolean eliminarTarea(String tarea) {
        return tareas.eliminar(tarea);
        
    }

    public boolean contieneTarea(String tarea) {
        return tareas.contiene(tarea);
    }

    public List<String> listarTareas() {
        return tareas.stream().collect(Collectors.toList());
    }

    public int contarTareas() {
        return tareas.obtenerTamaño();
    }
    
    
}