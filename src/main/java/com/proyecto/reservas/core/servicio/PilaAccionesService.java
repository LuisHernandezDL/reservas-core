/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.servicio;

/**
 *
 * @author Luis
 */
import com.mycompany.reservas.estructuras.pila.PilaAcciones;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.EmptyStackException;

@Service
public class PilaAccionesService {
    
    

    private final PilaAcciones<String> pila = new PilaAcciones<>();

        @Autowired
     private HistorialService historialService;

     public void registrarAccion(String accion) {
         pila.apilar(accion);
         historialService.registrarAccion("ACCION_REGISTRADA: " + accion, null);
     }

     public String deshacerUltimaAccion() {
         try {
             String accion = pila.desapilar();
             historialService.registrarAccion("ACCION_DESHECHA: " + accion, null);
             return accion;
         } catch (EmptyStackException e) {
             return null;
         }
     }

    public String verUltimaAccion() {
        try {
            return pila.cima();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    public boolean estaVacia() {
        return pila.estaVacia();
    }

    public int contarAcciones() {
        return pila.tamaño();
    }
}