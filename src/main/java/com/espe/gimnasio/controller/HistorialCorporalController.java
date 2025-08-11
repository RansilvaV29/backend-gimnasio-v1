package com.espe.gimnasio.controller;

import com.espe.gimnasio.dto.HistorialCorporalDto;
import com.espe.gimnasio.dto.HistorialCorporalResponseDto;
import com.espe.gimnasio.entity.Historialcorporal;
import com.espe.gimnasio.service.HistorialCorporalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-corporal")
public class HistorialCorporalController {

    @Autowired
    private HistorialCorporalService service;

    @PostMapping
    public ResponseEntity<HistorialCorporalResponseDto> crear(@RequestBody HistorialCorporalDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<HistorialCorporalResponseDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<List<HistorialCorporalResponseDto>> obtenerHistorialesPorUsuario(@PathVariable Integer idUsuario) {
        List<HistorialCorporalResponseDto> historiales = service.listarHistorialesPorUsuario(idUsuario);
        if(historiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historiales);
    }

}
