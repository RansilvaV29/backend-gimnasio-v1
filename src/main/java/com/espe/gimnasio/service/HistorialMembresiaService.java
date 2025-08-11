package com.espe.gimnasio.service;

import java.util.Calendar;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.espe.gimnasio.dto.HistorialMembresiaActivaDto;
import com.espe.gimnasio.dto.HistorialMembresiaDto;
import com.espe.gimnasio.entity.Historialmambresia;
import com.espe.gimnasio.entity.Membresia;
import com.espe.gimnasio.entity.Usuario;
import com.espe.gimnasio.repository.HistorialMembresiaRepository;
import com.espe.gimnasio.repository.MembresiaRepository;
import com.espe.gimnasio.repository.UsuarioRepository;

@Service
public class HistorialMembresiaService {
	@Autowired
	private HistorialMembresiaRepository historialMembresiaRepository;
	
	public HistorialMembresiaService(HistorialMembresiaRepository historialMembresiaRepository) {
        this.historialMembresiaRepository = historialMembresiaRepository;
    }
	
	@Autowired
	private MembresiaRepository membresiaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private NotificacionProducer notificacionProducer;
	
	//obtener membresia por usuario (solo la activa)
	public HistorialMembresiaActivaDto obtenerMembresiaActivaPorUsuario(Integer idUsuario) {
	    Historialmambresia historial = historialMembresiaRepository.findMembresiaActivaByUsuario(idUsuario)
	            .orElseThrow(() -> new RuntimeException("El usuario no tiene una membresía activa."));

	    return new HistorialMembresiaActivaDto(
	            historial.getIdHistorialMembresias(),
	            historial.getFechaInicio(),
	            historial.getFechaFin(),
	            historial.getEstado(),
	            historial.getValorPagado(),
	            historial.getMembresia().getPrecio(),
	            historial.getMembresia().getNombreMembresia(),
	            historial.getMembresia().getVigencia()
	    );
	}


	
	private Date calcularFechaFin(Date fechaInicio, String vigencia) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fechaInicio);

	    switch (vigencia.toLowerCase()) {
	        case "15 dias":
	        case "15 dias consecutivos":
	            calendar.add(Calendar.DAY_OF_MONTH, 15);
	            break;
	        case "1 mes":
	            calendar.add(Calendar.MONTH, 1);
	            break;
	        case "3 meses":
	            calendar.add(Calendar.MONTH, 3);
	            break;
	        case "6 meses":
	            calendar.add(Calendar.MONTH, 6);
	            break;
	        case "9 meses":
	            calendar.add(Calendar.MONTH, 9);
	            break;
	        case "12 meses":
	            calendar.add(Calendar.MONTH, 12);
	            break;
	        default:
	            throw new IllegalArgumentException("Vigencia no válida: " + vigencia);
	    }

	    return calendar.getTime();
	}


	
	public Historialmambresia registrarHistorial(HistorialMembresiaDto dto) {
		Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        Membresia membresia = membresiaRepository.findById(dto.getIdMembresia())
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada con ID: " + dto.getIdMembresia()));
        
        boolean tieneActiva = usuario.getHistorialmambresias()
                .stream()
                .anyMatch(historial -> Boolean.TRUE.equals(historial.getEstado()));

        if (tieneActiva) {
            throw new RuntimeException("El usuario ya tiene una membresía activa.");
        }
        
        Date fechaInicio = new Date();
        Date fechaFin = calcularFechaFin(fechaInicio, membresia.getVigencia());;
        
        Historialmambresia historial = new Historialmambresia();
        historial.setUsuario(usuario);
        historial.setMembresia(membresia);
        historial.setFechaInicio(fechaInicio);
        historial.setFechaFin(fechaFin);
        historial.setValorMembresiaactual(historial.getMembresia().getPrecio());
        historial.setValorPagado(dto.getValorPagado());
        historial.setEstado(dto.getEstado());
        notificacionProducer.enviarNotificacion("Nuevo historial registrado fecha de inicio:" + historial.getFechaInicio() + " fecha de fin: "+historial.getFechaFin() , "Tipo de membresia registrada: "+ historial.getMembresia().getNombreMembresia());
        return historialMembresiaRepository.save(historial);
    }
	
	
	//desactivar las membresias cuando ya haya pasado un dia de su fecha fin 
	//ej: fecha fin es 2025-06-16 se camviará su estado a false el 2025-06-17
	@Scheduled(cron = "0 37 23 * * *") // todos los días a medianoche (segundo-minuto-hora-diaDelMes-mes-diaDeLaSemana)
    public void desactivarMembresiasVencidas() {
        Date hoy = new Date();
        List<Historialmambresia> vencidas = historialMembresiaRepository
                .findByEstadoTrueAndFechaFinBefore(hoy);

        for (Historialmambresia h : vencidas) {
            h.setEstado(false);
        }

        historialMembresiaRepository.saveAll(vencidas);

        if (!vencidas.isEmpty()) {
            System.out.println("Se desactivaron " + vencidas.size() + " membresías vencidas.");
        }
    }
}
