/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.controlador;

/**
 *
 * @author Luis
 */
import com.proyecto.reservas.core.model.Historial;
import com.proyecto.reservas.core.servicio.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping
    public List<Historial> obtenerHistorial() {
        return historialService.obtenerHistorial();
    }
}