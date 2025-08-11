package com.espe.gimnasio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espe.gimnasio.dto.AuthRespuestaDto;
import com.espe.gimnasio.dto.LoginSolicitudDto;
import com.espe.gimnasio.dto.UpdateUserSolicitudDto;
import com.espe.gimnasio.dto.UsuarioDto;
import com.espe.gimnasio.entity.Usuario;
import com.espe.gimnasio.service.AuthService;
import com.espe.gimnasio.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            Usuario nuevo = authService.registrar(usuario);
            return ResponseEntity.ok(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<AuthRespuestaDto> login(@RequestBody LoginSolicitudDto request) {
        Usuario usuario = authService.autenticar(request.getCorreo(), request.getPassword());
        // Aquí se genera el token 
        String token = jwtService.generateToken(usuario.getCorreo(), usuario.getRol().getNombreRol());
        return ResponseEntity.ok(new AuthRespuestaDto(token));
    }
    
    
}
