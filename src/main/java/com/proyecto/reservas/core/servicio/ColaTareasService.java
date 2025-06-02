/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.servicio;

/**
 *
 * @author Luis
 */
import com.mycompany.reservas.estructuras.cola.ColaProgramada;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ColaTareasService {

    private final ColaProgramada<String> cola = new ColaProgramada<>();

        @Autowired
      private HistorialService historialService;

      public void agregarTarea(String tarea) {
          cola.encolar(tarea);
          historialService.registrarAccion("TAREA_ENCOLADA: " + tarea, null);
      }

      public String procesarTarea() {
          String tarea = cola.desencolar();
          historialService.registrarAccion("TAREA_PROCESADA: " + tarea, null);
          return tarea != null ? tarea : "No hay tareas en la cola";
      }
    public String verProximaTarea() {
        String tarea = cola.verFrente();
        return (tarea != null) ? tarea : "No hay tareas pendientes";
    }

    public int contarTareas() {
        return cola.obtenerTamaño();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }
}
