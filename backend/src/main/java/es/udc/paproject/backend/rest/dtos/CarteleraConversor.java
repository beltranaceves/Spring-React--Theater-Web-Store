package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.CarteleraItemDto;

public class CarteleraConversor {
	
	public final static CarteleraDto toCarteleraDto(CarteleraItemDto item) {
		
		List<CarteleraSesionDto> sesiones = new ArrayList<>();
		
		for (int i = 0; i < item.getSesiones().size(); i++) {			
			sesiones.add(new CarteleraSesionDto(item.getSesiones().get(i).getId(), toMillis(item.getSesiones().get(i).getHora())));
		}
		
		return new CarteleraDto(item.getPelicula().getId(), item.getPelicula().getTitulo(), sesiones);
	}
	
	public final static List<CarteleraDto> toCarteleraDtos(List<CarteleraItemDto> items) {
		return items.stream().map(c -> toCarteleraDto(c)).collect(Collectors.toList());
	}
	
	private final static long toMillis(LocalDateTime date) {
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}
}
