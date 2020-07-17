package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;


public class CriticaDto {
	
	private int puntuacion;
	private String titulo;
	private String comentario;
	private LocalDateTime fecha;
	private String tituloPelicula;
	Long peliculaId;
	
	CriticaDto() {}

	public CriticaDto(int puntuacion, String titulo, String comentario, String tituloPelicula, Long peliculaId, LocalDateTime fecha) {
		super();
		this.puntuacion = puntuacion;
		this.titulo = titulo;
		this.comentario = comentario;
		this.fecha = fecha;
		this.tituloPelicula = tituloPelicula;
		this.peliculaId = peliculaId;
	}
	
	public Long getPeliculaId() {
		return peliculaId;
	}

	public void setPeliculaId(Long peliculaId) {
		this.peliculaId = peliculaId;
	}

	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
	
	
}
