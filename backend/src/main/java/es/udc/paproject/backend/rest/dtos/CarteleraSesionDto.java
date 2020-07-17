package es.udc.paproject.backend.rest.dtos;

public class CarteleraSesionDto {

	private Long id;
	private long hora;	

	public CarteleraSesionDto() {

	}
	
	public CarteleraSesionDto(Long id, long hora) {;
		this.id = id;
		this.hora = hora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getHora() {
		return hora;
	}

	public void setHora(long hora) {
		this.hora = hora;
	}

}