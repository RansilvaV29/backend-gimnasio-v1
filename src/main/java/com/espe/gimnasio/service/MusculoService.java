package com.espe.gimnasio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espe.gimnasio.dto.MusculoDto;
import com.espe.gimnasio.entity.Musculo;
import com.espe.gimnasio.repository.MusculoRepository;

@Service
public class MusculoService {
	
	@Autowired
	private MusculoRepository musculoRepository;
	
	public MusculoDto registrarMusculo(MusculoDto musculoDto) {
		Musculo musculo = new Musculo();
		musculo.setNombre(musculoDto.getNombre());
		musculo = musculoRepository.save(musculo);
		
		return new MusculoDto(musculo.getId(), musculo.getNombre());
	}
	
	public List<MusculoDto> listarTodos(){
		return musculoRepository.findAll().stream()
				.map(m -> new MusculoDto(m.getId(), m.getNombre()))
				.collect(Collectors.toList());
	}
	
	public Optional<MusculoDto> buscarPorNombre(String nombre) {
        return musculoRepository.findByNombreIgnoreCase(nombre)
                .map(m -> new MusculoDto(m.getId(), m.getNombre()));
    }
}
