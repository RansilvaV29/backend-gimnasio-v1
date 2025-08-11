package com.espe.gimnasio.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espe.gimnasio.dto.EjercicioDto;
import com.espe.gimnasio.entity.Ejercicio;
import com.espe.gimnasio.repository.EjercicioRepository;
import com.espe.gimnasio.service.EjercicioService;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {
	
	@Autowired
    private EjercicioService ejercicioService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<EjercicioDto> crearEjercicio(
	        @RequestParam String nombre,
	        @RequestParam(required = false) String descripcion,
	        @RequestParam("gif") MultipartFile gif,
	        @RequestParam("musculoIds") List<Long> musculoIds
	) throws IOException {
	    EjercicioDto creado = ejercicioService.crearEjercicio(nombre, descripcion, gif, musculoIds);
	    return ResponseEntity.ok(creado);
	}
	
	@GetMapping(value = "/{id}/gif", produces = MediaType.IMAGE_GIF_VALUE)
	public ResponseEntity<byte[]> obtenerGif(@PathVariable Integer id) {
	    byte[] gif = ejercicioService.obtenerGifPorId(id);
	    return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(gif);
	}
	
	@GetMapping
	public ResponseEntity<List<EjercicioDto>> obtenerTodos() {
	    List<EjercicioDto> lista = ejercicioService.obtenerTodosSinGif();
	    return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EjercicioDto> obtenerEjercicioSinGif(@PathVariable Integer id) {
	    EjercicioDto dto = ejercicioService.obtenerEjercicioPorId(id);
	    return ResponseEntity.ok(dto);
	}


}
