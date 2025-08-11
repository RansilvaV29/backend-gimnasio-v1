package com.espe.gimnasio.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.espe.gimnasio.dto.DetalleRutinaDto;
import com.espe.gimnasio.dto.ListarRutinasDto;
import com.espe.gimnasio.dto.RutinaCrearDto;
import com.espe.gimnasio.entity.Ejercicio;
import com.espe.gimnasio.entity.Rutina;
import com.espe.gimnasio.entity.RutinaEjercicio;
import com.espe.gimnasio.repository.EjercicioRepository;
import com.espe.gimnasio.repository.RutinaEjercicioRepository;
import com.espe.gimnasio.repository.RutinaRepository;


@Service
public class RutinaService {

	@Autowired
	private RutinaRepository rutinaRepository;
	
	@Autowired
	private EjercicioRepository ejercicioRepository;
	
	@Autowired
	private RutinaEjercicioRepository rutinaEjercicioRepository;
	
	@Transactional
	public Rutina crearRutina(RutinaCrearDto dto) {
		Rutina rutina = new Rutina();
		
		rutina.setNombre(dto.getNombre());
		rutina.setDescripcion(dto.getDescripcion());
		rutina = rutinaRepository.save(rutina);
		
		//agregar ejercicio a la rutina
		for (DetalleRutinaDto detalleDto : dto.getEjercicios()) {
            Ejercicio ejercicio = ejercicioRepository.findById(detalleDto.getEjercicioId())
                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));

            RutinaEjercicio re = new RutinaEjercicio();
            re.setEjercicio(ejercicio);
            re.setRutina(rutina);
            re.setRepeticiones(detalleDto.getRepeticiones());
            re.setSeries(detalleDto.getSeries());

            rutinaEjercicioRepository.save(re);
        }
		
		return rutina;
	}
	
	public List<ListarRutinasDto> listarRutinasResumen() {
        return rutinaRepository.findAll().stream().map(r -> {
            ListarRutinasDto dto = new ListarRutinasDto();
            dto.setId(r.getId());
            dto.setNombre(r.getNombre());
            dto.setDescripcion(r.getDescripcion());
            dto.setCantidadEjercicios((long) r.getRutinaEjercicios().size());
            return dto;
        }).toList();
    }
	
	public List<RutinaCrearDto> obtenerTodasLasRutinas() {
	    List<Rutina> rutinas = rutinaRepository.findAll();

	    return rutinas.stream().map(rutina -> {
	        RutinaCrearDto dto = new RutinaCrearDto();
	        dto.setId(rutina.getId());
	        dto.setNombre(rutina.getNombre());
	        dto.setDescripcion(rutina.getDescripcion());

	        List<DetalleRutinaDto> ejercicios = rutina.getRutinaEjercicios().stream()
	            .map(re -> {
	                DetalleRutinaDto edto = new DetalleRutinaDto();
	                edto.setEjercicioId(re.getEjercicio().getId());
	                edto.setNombre(re.getEjercicio().getNombre());
	                edto.setDescripcion(re.getEjercicio().getDescripcion());
	                edto.setSeries(re.getSeries());
	                edto.setRepeticiones(re.getRepeticiones());
	                return edto;
	            }).toList();

	        dto.setEjercicios(ejercicios);
	        return dto;
	    }).toList();
	}


    public RutinaCrearDto obtenerDetalleRutina(Integer id) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

        RutinaCrearDto dto = new RutinaCrearDto();
        dto.setId(rutina.getId());
        dto.setNombre(rutina.getNombre());
        dto.setDescripcion(rutina.getDescripcion());

        List<DetalleRutinaDto> ejercicios = rutina.getRutinaEjercicios().stream()
                .map(re -> {
                	DetalleRutinaDto edto = new DetalleRutinaDto();
                    edto.setEjercicioId(re.getEjercicio().getId());
                    edto.setNombre(re.getEjercicio().getNombre());
                    edto.setDescripcion(re.getEjercicio().getDescripcion());
                    edto.setSeries(re.getSeries());
                    edto.setRepeticiones(re.getRepeticiones());
                    return edto;
                }).toList();

        dto.setEjercicios(ejercicios);
        return dto;
    }
    
    public List<ListarRutinasDto> buscarRutinasPorMusculo(Integer musculoId) {
        List<Rutina> rutinas = rutinaRepository.findByMusculoId(musculoId);

        return rutinas.stream().map(r -> {
        	ListarRutinasDto dto = new ListarRutinasDto();
            dto.setId(r.getId());
            dto.setNombre(r.getNombre());
            dto.setDescripcion(r.getDescripcion());
            dto.setCantidadEjercicios((long) r.getRutinaEjercicios().size());
            return dto;
        }).toList();
    }

}
