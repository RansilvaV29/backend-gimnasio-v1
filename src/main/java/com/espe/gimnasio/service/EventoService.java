package com.espe.gimnasio.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.espe.gimnasio.dto.CupoEventoDto;
import com.espe.gimnasio.dto.EventoDto;
import com.espe.gimnasio.entity.Evento;
import com.espe.gimnasio.entity.Usuario;
import com.espe.gimnasio.repository.EventoRepository;
import com.espe.gimnasio.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service 
public class EventoService {
	@Autowired
    private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
    public Evento crearEvento(EventoDto dto) {
    	
    	Date hoy = new Date();
        if (dto.getFecha().before(hoy)) {
            throw new RuntimeException("La fecha del evento no puede ser menor a la fecha actual.");
        }

        // Validar que horaFin > horaInicio
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date horaInicio = sdf.parse(dto.getHoraInicio());
            Date horaFin = sdf.parse(dto.getHorraFin());

            if (!horaFin.after(horaInicio)) {
                throw new RuntimeException("La hora de fin debe ser mayor que la hora de inicio.");
            }

        } catch (ParseException e) {
            throw new RuntimeException("Formato de hora inválido. Use el formato HH:mm (ejemplo: 14:30).");
        }
        Evento evento = new Evento();
        evento.setNombreEvento(dto.getNombreEvento());
        evento.setDescripcion(dto.getDescripcion());
        evento.setCupoMaximo(dto.getCupoMaximo());
        evento.setFecha(dto.getFecha());
        evento.setHoraInicio(dto.getHoraInicio());
        evento.setHorraFin(dto.getHorraFin());
        return eventoRepository.save(evento);
    }
    @Transactional
    public Evento agregarUsuarioAEvento(Integer idEvento, Integer idUsuario) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (evento.getUsuarios().contains(usuario)) {
            throw new RuntimeException("El usuario ya está inscrito en este evento.");
        }

        if (evento.getUsuarios().size() >= evento.getCupoMaximo()) {
            throw new RuntimeException("No hay cupos disponibles.");
        }

        evento.getUsuarios().add(usuario);
        usuario.getEventos().add(evento); // mantener bidireccionalidad

        eventoRepository.save(evento);
        usuarioRepository.save(usuario);

        // Notificar a todos los clientes sobre los cupos disponibles
        int cuposDisponibles = evento.getCupoMaximo() - evento.getUsuarios().size();
        CupoEventoDto cupoDto = new CupoEventoDto(evento.getIdEventos(), cuposDisponibles);
        messagingTemplate.convertAndSend("/topic/cupos", cupoDto);

        return evento;
    }

    public List<EventoDto> obtenerTodosEventos() {
        List<Evento> eventos = eventoRepository.findAll();

        return eventos.stream().map(evento -> {
            EventoDto dto = new EventoDto();
            dto.setIdEvento(evento.getIdEventos() != null ? evento.getIdEventos().longValue() : null);
            dto.setNombreEvento(evento.getNombreEvento());
            dto.setDescripcion(evento.getDescripcion());
            dto.setCupoMaximo(evento.getCupoMaximo());
            dto.setFecha(evento.getFecha());
            dto.setHoraInicio(evento.getHoraInicio());
            dto.setHorraFin(evento.getHorraFin());
            dto.setCuposDisponibles(evento.getCupoMaximo() - evento.getUsuarios().size());

            return dto;
        }).collect(Collectors.toList());
    }
    
    public CupoEventoDto obtenerCupoActual(Integer idEvento) {
        Evento evento = eventoRepository.findById(idEvento)
            .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        int cuposDisponibles = evento.getCupoMaximo() - evento.getUsuarios().size();
        return new CupoEventoDto(evento.getIdEventos(), cuposDisponibles);
    }

}
