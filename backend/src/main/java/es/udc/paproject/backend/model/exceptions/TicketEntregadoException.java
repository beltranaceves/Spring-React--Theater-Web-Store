package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class TicketEntregadoException extends Exception {
	
	private Long compraId;

	public TicketEntregadoException(Long compraId) {
		this.compraId = compraId;
	}

	public Long getCompraId() {
		return compraId;
	}
}
