/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.servicio;

/**
 *
 * @author Luis
 */
import com.mycompany.reservas.estructuras.arbol.ArbolTareas;
import com.mycompany.reservas.estructuras.arbol.ArbolTareas.Nodo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@Service
public class ArbolTareasService {

    private ArbolTareas<String> arbol;

    @Autowired
    private HistorialService historialService;

    public void inicializarArbol(String tareaRaiz) {
        arbol = new ArbolTareas<>(tareaRaiz);
        historialService.registrarAccion("ÁRBOL_INICIALIZADO con raíz: " + tareaRaiz, null);
    }
    public Nodo<String> obtenerRaiz() {
        return arbol != null ? arbol.getRaiz() : null;
    }

    // Busca un nodo por su dato (nombre de la tarea)
    public Optional<Nodo<String>> buscarNodo(Nodo<String> nodo, String datoBuscado) {
        if (nodo.getDato().equals(datoBuscado)) {
            return Optional.of(nodo);
        }
        for (Nodo<String> hijo : nodo.getHijos()) {
            Optional<Nodo<String>> resultado = buscarNodo(hijo, datoBuscado);
            if (resultado.isPresent()) {
                return resultado;
            }
        }
        return Optional.empty();
    }

   public boolean agregarTarea(String tareaPadre, String nuevaTarea) {
    if (arbol == null) return false;

    Optional<Nodo<String>> nodoPadre = buscarNodo(arbol.getRaiz(), tareaPadre);
    if (nodoPadre.isPresent()) {
        arbol.agregarHijo(nodoPadre.get(), nuevaTarea);
        historialService.registrarAccion("TAREA_AGREGADA_AL_ÁRBOL: " + nuevaTarea, null);
        return true;
    }
    return false;
}

    public String imprimirArbol() {
        if (arbol == null) return "Árbol no inicializado.";

        StringBuilder sb = new StringBuilder();
        imprimirRecursivo(arbol.getRaiz(), 0, sb);
        return sb.toString();
    }

    private void imprimirRecursivo(Nodo<String> nodo, int nivel, StringBuilder sb) {
        if (nodo == null) return;

        sb.append("  ".repeat(nivel)).append("- ").append(nodo.getDato()).append("\n");
        for (Nodo<String> hijo : nodo.getHijos()) {
            imprimirRecursivo(hijo, nivel + 1, sb);
        }
    }
}