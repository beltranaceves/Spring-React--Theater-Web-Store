package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class SesionDto {
	
	// sobra id, falta: duración, nombre de cine (apartado 2.3)

	private long hora;
	private BigDecimal precio;
	private String pelicula;
	private String sala;
	private int butacasLibres;
	private int duracion;
	private String cine;
	
	public SesionDto(){}

	public SesionDto(long hora, BigDecimal precio, String pelicula, String sala, int butacasLibres, int duracion, String cine) {
		this.hora = hora;
		this.precio = precio;
		this.pelicula = pelicula;
		this.sala = sala;
		this.butacasLibres = butacasLibres;
		this.duracion = duracion;
		this.cine = cine;
	}

	public long getHora() {
		return hora;
	}

	public void setHora(long hora) {
		this.hora = hora;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public int getButacasLibres() {
		return butacasLibres;
	}

	public void setButacasLibres(int butacasLibres) {
		this.butacasLibres = butacasLibres;
	}
	
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public String getCine() {
		return cine;
	}
	
	public void setCine(String cine) {
		this.cine = cine;
	}
}
