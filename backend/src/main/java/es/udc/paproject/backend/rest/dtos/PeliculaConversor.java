package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Pelicula;

public class PeliculaConversor {
	
	private PeliculaConversor() {}
	
	public final static PeliculaDto toPeliculaDto(Pelicula pelicula, Double valoracion, int numeroCriticas) {
		return new PeliculaDto(pelicula.getId(), pelicula.getTitulo(), pelicula.getDuracion(), pelicula.getResumen(), valoracion, numeroCriticas);
	}
}
