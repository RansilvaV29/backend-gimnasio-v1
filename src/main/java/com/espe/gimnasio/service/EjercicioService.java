package com.espe.gimnasio.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.espe.gimnasio.dto.EjercicioDto;
import com.espe.gimnasio.entity.Ejercicio;
import com.espe.gimnasio.entity.EjercicioMusculo;
import com.espe.gimnasio.entity.Musculo;
import com.espe.gimnasio.repository.EjercicioMusculoRepository;
import com.espe.gimnasio.repository.EjercicioRepository;
import com.espe.gimnasio.repository.MusculoRepository;

@Service
public class EjercicioService {
	@Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private MusculoRepository musculoRepository;
    
    @Autowired
    private EjercicioMusculoRepository ejercicioMusculoRepository;
    
    public EjercicioDto crearEjercicio(String nombre, String descripcion, MultipartFile gif, List<Long> musculoIds) throws IOException {
        // 1. Crear y guardar el ejercicio
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre(nombre);
        ejercicio.setDescripcion(descripcion);
        ejercicio.setGif(gif.getBytes());

        ejercicio = ejercicioRepository.save(ejercicio); // importante: debe tener ID para las relaciones

        // 2. Asociar músculos (crear EjercicioMusculo por cada uno)
        for (Long musculoId : musculoIds) {
            Musculo musculo = musculoRepository.findById(musculoId.intValue())
                .orElseThrow(() -> new RuntimeException("Músculo no encontrado: " + musculoId));

            EjercicioMusculo em = new EjercicioMusculo();
            em.setEjercicio(ejercicio);
            em.setMusculo(musculo);
            // ID puede ser autogenerado si así está tu base (usa SERIAL)
            ejercicioMusculoRepository.save(em);
        }

        // 3. Construir DTO de respuesta
        EjercicioDto dto = new EjercicioDto();
        dto.setId(ejercicio.getId());
        dto.setNombre(ejercicio.getNombre());
        dto.setDescripcion(ejercicio.getDescripcion());

        List<String> musculos = ejercicioMusculoRepository.findByEjercicioId(ejercicio.getId()).stream()
            .map(em -> em.getMusculo().getNombre())
            .collect(Collectors.toList());

        dto.setMusculos(musculos);

        return dto;
    }
    

    public List<EjercicioDto> obtenerTodosSinGif() {
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();

        return ejercicios.stream().map(ejercicio -> {
            EjercicioDto dto = new EjercicioDto();
            dto.setId(ejercicio.getId());
            dto.setNombre(ejercicio.getNombre());
            dto.setDescripcion(ejercicio.getDescripcion());

            List<String> musculos = ejercicio.getEjercicioMusculos().stream()
                .map(em -> em.getMusculo().getNombre())
                .toList();

            dto.setMusculos(musculos);

            return dto;
        }).toList();
    }
    
    public EjercicioDto obtenerEjercicioPorId(Integer id) {
    	Ejercicio ejercicio = ejercicioRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));
    	EjercicioDto dto = new EjercicioDto();
    	dto.setId(ejercicio.getId());
    	dto.setNombre(ejercicio.getNombre());
    	dto.setDescripcion(ejercicio.getDescripcion());
    	
    	List<String> musculos = ejercicio.getEjercicioMusculos().stream()
    			.map(em->em.getMusculo().getNombre())
    			.toList();
    	
    	dto.setMusculos(musculos);
    	
    	return dto;
    }
    
    public byte[] obtenerGifPorId(Integer id) {
        Ejercicio ejercicio = ejercicioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con id: " + id));

        return ejercicio.getGif();
    }
}
