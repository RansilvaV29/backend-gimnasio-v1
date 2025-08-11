package com.espe.gimnasio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espe.gimnasio.dto.UpdateUserSolicitudDto;
import com.espe.gimnasio.dto.UsuarioDto;
import com.espe.gimnasio.entity.Usuario;
import com.espe.gimnasio.service.JwtService;
import com.espe.gimnasio.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private JwtService jwtService;
	
	@PutMapping("/edit/{id}")
    public ResponseEntity<Usuario> editUser(@PathVariable Integer id, @RequestBody UpdateUserSolicitudDto request) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, request);
        return ResponseEntity.ok(actualizado);
    }
	
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> getTodas() {
        List<UsuarioDto> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
	
    @GetMapping("/usuariosPorEmail/{correo}")
    public ResponseEntity<UsuarioDto> getPorCedula(@PathVariable String correo) {
        UsuarioDto dto = usuarioService.obtenerPorCorreo(correo);
        return ResponseEntity.ok(dto);
    }
    
    
}
