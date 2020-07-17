package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PeliculaEmpezadaException;
import es.udc.paproject.backend.model.exceptions.SalaLlenaException;
import es.udc.paproject.backend.model.exceptions.TarjetaInvalidaException;
import es.udc.paproject.backend.model.exceptions.TarjetaNoCorrespondeException;
import es.udc.paproject.backend.model.exceptions.TicketEntregadoException;
import es.udc.paproject.backend.model.entities.Compra;

public interface CompraService {

	Compra buy(Long userId, Long sesionId, int quantity, String creditCard)
			throws InstanceNotFoundException, SalaLlenaException, PeliculaEmpezadaException, TarjetaInvalidaException;

	Block<Compra> findComprasByUser(Long userId, int page, int size);

	void entregarEntradas(Long compraId, String tarjeta) throws InstanceNotFoundException, PeliculaEmpezadaException,
			TicketEntregadoException, TarjetaNoCorrespondeException;
}
