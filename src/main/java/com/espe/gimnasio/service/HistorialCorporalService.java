package com.espe.gimnasio.service;

import com.espe.gimnasio.dto.HistorialCorporalDto;
import com.espe.gimnasio.dto.HistorialCorporalResponseDto;
import com.espe.gimnasio.entity.Historialcorporal;
import com.espe.gimnasio.entity.Usuario;
import com.espe.gimnasio.repository.HistorialCorporalRepository;
import com.espe.gimnasio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistorialCorporalService {

    @Autowired
    private HistorialCorporalRepository historialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public HistorialCorporalResponseDto crear(HistorialCorporalDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        Historialcorporal historial = new Historialcorporal();
        historial.setFechaControl(new Date());
        historial.setPesoKg(dto.getPesoKg());
        historial.setUsuario(usuario);

        historial = historialRepository.save(historial);

        // Armar el DTO de respuesta
        HistorialCorporalResponseDto response = new HistorialCorporalResponseDto();
        response.setIdHistorial(historial.getIdHistorial());
        response.setFechaControl(historial.getFechaControl());
        response.setPesoKg(historial.getPesoKg());
        response.setNombreUsuario(usuario.getNombres() + " " + usuario.getApellidos());

        return response;
    }



    public List<HistorialCorporalResponseDto> listarTodos() {
        List<Historialcorporal> historiales = historialRepository.findAll();
        return historiales.stream().map(historial -> {
            HistorialCorporalResponseDto dto = new HistorialCorporalResponseDto();
            dto.setIdHistorial(historial.getIdHistorial());
            dto.setFechaControl(historial.getFechaControl());
            dto.setPesoKg(historial.getPesoKg());
            dto.setNombreUsuario(historial.getUsuario().getNombres() + " " + historial.getUsuario().getApellidos());
            return dto;
        }).toList();
    }


    public Optional<HistorialCorporalResponseDto> obtenerPorId(Integer id) {
        return historialRepository.findById(id).map(historial -> {
            HistorialCorporalResponseDto dto = new HistorialCorporalResponseDto();
            dto.setIdHistorial(historial.getIdHistorial());
            dto.setFechaControl(historial.getFechaControl());
            dto.setPesoKg(historial.getPesoKg());
            dto.setNombreUsuario(historial.getUsuario().getNombres() + " " + historial.getUsuario().getApellidos());
            return dto;
        });
    }
    
    public List<HistorialCorporalResponseDto> listarHistorialesPorUsuario(Integer idUsuario) {
        List<Historialcorporal> historiales = historialRepository.findByUsuario_IdUsuario(idUsuario);

        return historiales.stream().map(historial -> {
            HistorialCorporalResponseDto dto = new HistorialCorporalResponseDto();
            dto.setIdHistorial(historial.getIdHistorial());
            dto.setFechaControl(historial.getFechaControl());
            dto.setPesoKg(historial.getPesoKg());
            dto.setNombreUsuario(historial.getUsuario().getNombres() + " " + historial.getUsuario().getApellidos());
            return dto;
        }).collect(Collectors.toList());
    }
}
