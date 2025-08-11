package com.espe.gimnasio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espe.gimnasio.dto.CupoEventoDto;
import com.espe.gimnasio.dto.EventoDto;
import com.espe.gimnasio.entity.Evento;
import com.espe.gimnasio.service.EventoService;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {
	@Autowired
    private EventoService eventoService;
	
	@GetMapping
    public List<EventoDto> getTodosEventos() {
        return eventoService.obtenerTodosEventos();
    }

	@PostMapping
    public ResponseEntity<?> crearEvento(@RequestBody EventoDto dto) {
        try {
            Evento evento = eventoService.crearEvento(dto);
            return ResponseEntity.ok(evento);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                        "message", "mensaje " + ex.getMessage()
                    ));
            }
    }
	
	@PostMapping("/{idEvento}/agregar-usuario/{idUsuario}")
    public ResponseEntity<Evento> agregarUsuario(@PathVariable Integer idEvento, @PathVariable Integer idUsuario) {
        Evento eventoActualizado = eventoService.agregarUsuarioAEvento(idEvento, idUsuario);
        return ResponseEntity.ok(eventoActualizado);
    }
	
	@GetMapping("/{idEvento}/cupos")
    public CupoEventoDto getCupos(@PathVariable Integer idEvento) {
        return eventoService.obtenerCupoActual(idEvento);
    }
	
	
}
