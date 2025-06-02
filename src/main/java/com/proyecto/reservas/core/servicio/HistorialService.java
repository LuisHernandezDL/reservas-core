/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.servicio;

/**
 *
 * @author Luis
 */
import com.proyecto.reservas.core.model.Historial;
import com.proyecto.reservas.core.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    public void registrarAccion(String accion, Object usuario) {
        Historial historial = new Historial();
        historial.setAccion(accion);
        historial.setFecha(LocalDateTime.now());
        //historial.setUsuario(usuario); // si se usa en el futuro
        historialRepository.save(historial);
    }

    public List<Historial> obtenerHistorial() {
        return historialRepository.findAll();
    }
}