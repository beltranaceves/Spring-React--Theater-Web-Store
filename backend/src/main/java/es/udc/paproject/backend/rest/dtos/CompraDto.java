package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class CompraDto {

	private long fecha;
	private String cine;
	private String tituloPelicula;
	private int localidades;
	private BigDecimal precioTotal;
	private long fechaSesion;

	public CompraDto() {}

	public CompraDto(long fecha, String cine, String tituloPelicula, int localidades, BigDecimal precioTotal,
			long fechaSesion) {

		this.fecha = fecha;
		this.cine = cine;
		this.tituloPelicula = tituloPelicula;
		this.localidades = localidades;
		this.precioTotal = precioTotal;
		this.fechaSesion = fechaSesion;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}
	
	public String getCine() {
		return cine;
	}
	
	public void setCine(String cine) {
		this.cine = cine;
	}

	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}
	
	public int getLocalidades() {
		return localidades;
	}

	public void setLocalidades(int localidades) {
		this.localidades = localidades;
	}
	
	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public long getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(long fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

}
