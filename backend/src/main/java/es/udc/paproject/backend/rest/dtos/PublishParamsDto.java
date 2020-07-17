package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;

public class PublishParamsDto {
	
	private int puntuacion;
	private String titulo;
	private String comentario;
	private Long peliculaId;
	
	@NotNull
	public int getPuntuacion() {
		return puntuacion;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	@NotNull
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@NotNull
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@NotNull
	public Long getPeliculaId() {
		return peliculaId;
	}
	
	public void setPelicula(Long peliculaId) {
		this.peliculaId = peliculaId;
	}
	
}
