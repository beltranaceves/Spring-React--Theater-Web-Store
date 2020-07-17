package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class SalaLlenaException extends Exception {
	
	private int entradas;
	
	public SalaLlenaException(Long sesionId, int entradas) {
		this.entradas = entradas;
	}
	
	public int getEntradas() {
		return entradas;
	}
}
