package com.espe.gimnasio.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.espe.gimnasio.dto.UpdateUserSolicitudDto;
import com.espe.gimnasio.dto.UsuarioDto;
import com.espe.gimnasio.entity.Rol;
import com.espe.gimnasio.entity.Usuario;
import com.espe.gimnasio.repository.RolRepository;
import com.espe.gimnasio.repository.UsuarioRepository;


@Service
public class UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
    private RolRepository rolRepository;
	
	
	public UsuarioDto obtenerPorCorreo(String correo) {
    	Usuario usuario = usuarioRepository.findByCorreo(correo)
    			.orElseThrow(()-> new RuntimeException("Uusario no encontrado"));
    	
    	return new UsuarioDto(
    			usuario.getIdUsuario(),
    			usuario.getCorreo(),
    			usuario.getNombres(),
    			usuario.getApellidos(),
    			usuario.getTelefono(),
    			usuario.getEdad(),
    			usuario.getCedula(),
    			usuario.getRol().getIdRol(),
    			usuario.getFechaNacimiento(),
    			usuario.getFechaIngreso()
    			);
    }
	
	public List<UsuarioDto> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        
        // Mapear a DTOs manualmente
        return usuarios.stream()
                .map(p -> new UsuarioDto(
                		p.getIdUsuario(),
            			p.getCorreo(),
            			p.getNombres(),
            			p.getApellidos(),
            			p.getTelefono(),
            			p.getEdad(),
            			p.getCedula(),
            			p.getRol().getIdRol(),
            			p.getFechaNacimiento(),
            			p.getFechaIngreso()))
                .collect(Collectors.toList());
    }
	
	public Usuario actualizarUsuario(Integer idUsuario, UpdateUserSolicitudDto request) {
        
		
		Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ID: " + idUsuario));

        if (request.getCorreo() != null && !request.getCorreo().isEmpty()) {
            usuario.setCorreo(request.getCorreo());
        }
        if (request.getNombres() != null && !request.getNombres().isEmpty()) {
            usuario.setNombres(request.getNombres());
        }
        if (request.getApellidos() != null && !request.getApellidos().isEmpty()) {
            usuario.setApellidos(request.getApellidos());
        }
        if (request.getTelefono() != null && !request.getTelefono().isEmpty()) {
            usuario.setTelefono(request.getTelefono());
        }
        if (request.getEdad() != null) {
            usuario.setEdad(request.getEdad());
        }
        if (request.getCedula() != null && !request.getCedula().isEmpty()) {
            usuario.setCedula(request.getCedula());
        }
        if (request.getIdRol() != null) {
            Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + request.getIdRol()));
            usuario.setRol(rol);
        }

        
        return usuarioRepository.save(usuario);
    }
}
