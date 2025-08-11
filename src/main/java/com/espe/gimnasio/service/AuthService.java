package com.espe.gimnasio.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.espe.gimnasio.dto.UpdateUserSolicitudDto;
import com.espe.gimnasio.dto.UsuarioDto;
import com.espe.gimnasio.entity.Rol;
import com.espe.gimnasio.entity.Usuario;
import com.espe.gimnasio.repository.RolRepository;
import com.espe.gimnasio.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrar(Usuario usuario) {
        Date fechaNacimiento = usuario.getFechaNacimiento();
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }

        Date hoy = new Date();

        // Fecha menor a hoy
        if (fechaNacimiento.after(hoy)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }

        // Fecha mayor a 01-01-1950
        Calendar limiteInferior = Calendar.getInstance();
        limiteInferior.set(1950, Calendar.JANUARY, 1); // 1 de enero de 1950
        //calculo de la edad 
        LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoyLocal = LocalDate.now();
        int edad = Period.between(fechaNac, hoyLocal).getYears();
        if (fechaNacimiento.before(limiteInferior.getTime())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser anterior a 1950.");
        }
        
        usuario.setEdad(edad);
        usuario.setFechaIngreso(hoy); // Fecha actual como fecha de ingreso
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        return usuarioRepository.save(usuario);
    }


    public Usuario autenticar(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getContrasena())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        return usuario;
    }
    
}
