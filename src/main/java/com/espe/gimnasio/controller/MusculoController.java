package com.espe.gimnasio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espe.gimnasio.dto.MusculoDto;
import com.espe.gimnasio.service.MusculoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/musculos")
public class MusculoController {
	@Autowired
	private MusculoService musculoService;
	
	@PostMapping
	public ResponseEntity<MusculoDto> crear(@RequestBody MusculoDto musculoDto){
		MusculoDto creado = musculoService.registrarMusculo(musculoDto);
		return ResponseEntity.ok(creado);
	}
	
	@GetMapping
	public ResponseEntity<List<MusculoDto>> listar() {
        return ResponseEntity.ok(musculoService.listarTodos());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<MusculoDto> buscar(@PathVariable String nombre) {
        return musculoService.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
