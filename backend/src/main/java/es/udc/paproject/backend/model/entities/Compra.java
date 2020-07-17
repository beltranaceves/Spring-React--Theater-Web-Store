package es.udc.paproject.backend.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.FetchType;

@Entity
public class Compra {

	public static final int MAX_QUANTITY = 10;

	private Long id;
	private int localidades;
	private Sesion sesion;
	private User user;
	private String tarjeta;
	private LocalDateTime fecha;
	//private BigDecimal precioTotal;
	private boolean vendida;

	public Compra() {
	}

	public Compra(int localidades, Sesion sesion, User user, String tarjeta, LocalDateTime fecha) {
		
		this.sesion = sesion;
		this.user = user;
		this.tarjeta = tarjeta;
		this.fecha = fecha;
		this.vendida = false;
		this.localidades = localidades;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLocalidades() {
		return localidades;
	}

	public void setLocalidades(int localidades) {
		this.localidades = localidades;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "sesionId")
	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public boolean isVendida() {
		return vendida;
	}

	public void setVendida(boolean vendida) {
		this.vendida = vendida;
	}
	
	@Transient
	public BigDecimal getPrecioTotal() {
		return sesion.getPrecio().multiply(new BigDecimal(localidades));
	}
}
