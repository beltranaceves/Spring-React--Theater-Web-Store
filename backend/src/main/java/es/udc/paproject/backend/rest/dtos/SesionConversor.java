package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Sesion;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class SesionConversor {

	private SesionConversor() {}
	
	public final static SesionDto toSesionDto(Sesion sesion) {
		return new SesionDto(toMillis(sesion.getHora()), sesion.getPrecio(),
				sesion.getPelicula().getTitulo(), sesion.getSala().getNombre(),
				sesion.getButacasLibres(), sesion.getPelicula().getDuracion(),
				sesion.getSala().getCine().getNombre());
	}
	
	private final static long toMillis(LocalDateTime date) {
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}
}