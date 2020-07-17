package es.udc.paproject.backend.model.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
@org.hibernate.annotations.BatchSize(size = 10)
public class Critica {
	
	private Long id;
	private int puntuacion;
	private String titulo;
	private String comentario;
	private LocalDateTime fecha;
	private Pelicula pelicula;
	private User user;
	
	public Critica() {}
	
	public Critica(int puntuacion, String titulo, String comentario, User user, Pelicula pelicula, LocalDateTime fecha) {
		this.puntuacion = puntuacion;
		this.titulo = titulo;
		this.comentario = comentario;
		this.user = user;
		this.pelicula = pelicula;
		this.fecha = fecha;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha= fecha;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "peliculaId")
	public Pelicula getPelicula() {
		return pelicula;
	}
	
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	
	

}



