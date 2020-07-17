package es.udc.paproject.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class CarteleraItemDto {
	
	Pelicula pelicula;
	List<Sesion> sesiones;

	public CarteleraItemDto(Pelicula pelicula, List<Sesion> sesiones) {
		this.pelicula = pelicula;
		this.sesiones = sesiones;
	}

	public CarteleraItemDto() {}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	public void setSesion(Sesion sesion) {
		List<Sesion> sesiones = new ArrayList<>();
		sesiones.add(sesion);
		this.sesiones = sesiones;
	}
}
