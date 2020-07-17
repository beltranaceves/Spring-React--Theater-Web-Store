package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Critica;

public class CriticaConversor {

	private CriticaConversor() {
	}

	public final static List<CriticaDto> toCriticaDtos(List<Critica> criticas) {

		return criticas.stream().map(o -> toCriticaDto(o)).collect(Collectors.toList());
	}

	public final static CriticaDto toCriticaDto(Critica critica) {

		return new CriticaDto(critica.getPuntuacion(), critica.getTitulo(), critica.getComentario(), critica.getPelicula().getTitulo(), critica.getPelicula().getId(), critica.getFecha());
	}
	
}
