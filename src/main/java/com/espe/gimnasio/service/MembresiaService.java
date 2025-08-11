package com.espe.gimnasio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espe.gimnasio.dto.MembresiaDto;
import com.espe.gimnasio.entity.Membresia;
import com.espe.gimnasio.repository.MembresiaRepository;

@Service
public class MembresiaService {
	@Autowired
	private MembresiaRepository membresiaRepository;

	public Membresia registrar(MembresiaDto membresiaDto) {
		Membresia membresia = new Membresia();
		membresia.setNombreMembresia(membresiaDto.getNombreMembresia());
		membresia.setDescripcion(membresiaDto.getDescripcion());
		membresia.setPrecio(membresiaDto.getPrecio());
		membresia.setVigencia(membresiaDto.getVigencia());

		return membresiaRepository.save(membresia);
	}

	public Membresia editarMembresia(Integer id, MembresiaDto membresiaDto) {
		Membresia membresia = membresiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Membresía no encontrada con ID: " + id));

		membresia.setNombreMembresia(membresiaDto.getNombreMembresia());
		membresia.setDescripcion(membresiaDto.getDescripcion());
		membresia.setPrecio(membresiaDto.getPrecio());
		membresia.setVigencia(membresiaDto.getVigencia());

		return membresiaRepository.save(membresia);
	}

	public List<MembresiaDto> listarTodasComoDto() {
		return membresiaRepository.findAll().stream().map(m -> {
			MembresiaDto dto = new MembresiaDto();
			dto.setId(m.getIdMembresia());
			dto.setNombreMembresia(m.getNombreMembresia());
			dto.setDescripcion(m.getDescripcion());
			dto.setPrecio(m.getPrecio());
			dto.setVigencia(m.getVigencia());
			return dto;
		}).collect(Collectors.toList());
	}
	
	public MembresiaDto obtenerMembresiaPorId(Integer id) {
	    Membresia membresia = membresiaRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Membresía no encontrada con ID: " + id));

	    MembresiaDto dto = new MembresiaDto();
	    dto.setId(membresia.getIdMembresia());
	    dto.setNombreMembresia(membresia.getNombreMembresia());
	    dto.setDescripcion(membresia.getDescripcion());
	    dto.setPrecio(membresia.getPrecio());
	    dto.setVigencia(membresia.getVigencia());
	    
	    return dto;
	}

}
