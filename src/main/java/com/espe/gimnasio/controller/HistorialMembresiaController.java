package com.espe.gimnasio.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espe.gimnasio.dto.HistorialMembresiaActivaDto;
import com.espe.gimnasio.dto.HistorialMembresiaDto;
import com.espe.gimnasio.entity.Historialmambresia;
import com.espe.gimnasio.service.HistorialMembresiaService;

@RestController
@RequestMapping("/api/historial-membresias")
public class HistorialMembresiaController {
	@Autowired
    private HistorialMembresiaService historialService;

    @PostMapping
    public ResponseEntity<?> registrarHistorial(@RequestBody HistorialMembresiaDto dto) {
    	try {
            Historialmambresia historial = historialService.registrarHistorial(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
            		.body(Map.of("message", "Membresia asignada al usuario"));
            //return ResponseEntity.ok(historial);
        } catch (RuntimeException e) {
        	return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));

        }
    }
    @GetMapping("/activa/{idUsuario}")
    public ResponseEntity<HistorialMembresiaActivaDto> obtenerMembresiaActiva(@PathVariable Integer idUsuario) {
    	HistorialMembresiaActivaDto dto = historialService.obtenerMembresiaActivaPorUsuario(idUsuario);
        return ResponseEntity.ok(dto);
    }


}
