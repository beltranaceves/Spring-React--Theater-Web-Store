package es.udc.paproject.backend.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Version;

@Entity
@org.hibernate.annotations.BatchSize(size = 10)
public class Sesion {

	private Long id;
	private LocalDateTime hora;
	private BigDecimal precio;
	private Pelicula pelicula;
	private Sala sala;
	private int butacasLibres;

	private Long version;

	public Sesion() {
	}

	public Sesion(LocalDateTime hora, BigDecimal precio, Pelicula pelicula, Sala sala, int butacasLibres) {
		this.hora = hora;
		this.precio = precio;
		this.pelicula = pelicula;
		this.sala = sala;
		this.butacasLibres = butacasLibres;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "peliculaId")
	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "salaId")
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getButacasLibres() {
		return butacasLibres;
	}

	public void setButacasLibres(int butacasLibres) {
		this.butacasLibres = butacasLibres;
	}

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
