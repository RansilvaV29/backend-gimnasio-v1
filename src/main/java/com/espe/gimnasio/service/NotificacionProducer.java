package com.espe.gimnasio.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espe.gimnasio.dto.NotificacionDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificacionProducer {
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private ObjectMapper mapper;
	
	public void enviarNotificacion(String mensaje, String tipo) {
		try {
			NotificacionDto notificacionDto = new NotificacionDto(mensaje, tipo);
			String json = mapper.writeValueAsString(notificacionDto);
			template.convertAndSend("notificaciones.cola", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
