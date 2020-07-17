package es.udc.paproject.backend.rest.dtos;

public class PeliculaDto {
	
	private String titulo;
	private int duracion;
	private String resumen;
	private Double valoracion;
	private int numeroCriticas;

	public PeliculaDto() {
	}

	public PeliculaDto(Long id, String titulo, int duracion, String resumen, Double valoracion, int numeroCriticas) {
		this.titulo = titulo;
		this.duracion = duracion;
		this.resumen = resumen;
		this.valoracion = valoracion;
		this.numeroCriticas = numeroCriticas;
	}
	
	public int getNumeroCriticas() {
		return numeroCriticas;
	}

	public void setNumeroCriticas(int numeroCriticas) {
		this.numeroCriticas = numeroCriticas;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
}
