package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class CarteleraDto {
	Long idPelicula;
	String pelicula;
	List<CarteleraSesionDto> sesiones;
	
	public CarteleraDto(Long idPelicula, String pelicula, List<CarteleraSesionDto> sesiones) {
		this.idPelicula = idPelicula;
		this.pelicula = pelicula;
		this.sesiones = sesiones;
	}

	public Long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public List<CarteleraSesionDto> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<CarteleraSesionDto> sesiones) {
		this.sesiones = sesiones;
	}

	/*
	 * public List<Long> getIdsSesiones() { return idsSesiones; }
	 * 
	 * public void setIdsSesiones(List<Long> idsSesiones) { this.idsSesiones =
	 * idsSesiones; }
	 * 
	 * public List<LocalTime> getHoras() { return horas; }
	 * 
	 * public void setHoras(List<LocalTime> horas) { this.horas = horas; }
	 */
	
}