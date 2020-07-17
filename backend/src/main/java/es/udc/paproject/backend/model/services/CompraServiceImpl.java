package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PeliculaEmpezadaException;
import es.udc.paproject.backend.model.exceptions.SalaLlenaException;
import es.udc.paproject.backend.model.exceptions.TarjetaInvalidaException;
import es.udc.paproject.backend.model.exceptions.TarjetaNoCorrespondeException;
import es.udc.paproject.backend.model.exceptions.TicketEntregadoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import es.udc.paproject.backend.model.entities.Compra;
import es.udc.paproject.backend.model.entities.CompraDao;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.entities.SesionDao;
import es.udc.paproject.backend.model.entities.User;

@Service
@Transactional
public class CompraServiceImpl implements CompraService {

	@Autowired
	private PermissionChecker permissionChecker;

	@Autowired
	private SesionDao sesionDao;

	@Autowired
	private CompraDao compraDao;

	@Override
	public Compra buy(Long userId, Long sesionId, int localidades, String tarjeta)
			throws InstanceNotFoundException, SalaLlenaException,
			PeliculaEmpezadaException, TarjetaInvalidaException{

		// permissionChecker.checkUserExists(userId);
		User user = permissionChecker.checkUser(userId);
		Optional<Sesion> sesion = sesionDao.findById(sesionId);

		if (!sesion.isPresent()) {
			throw new InstanceNotFoundException("sesion", sesionId);
		}

		if (sesion.get().getHora().isBefore(LocalDateTime.now())) {
			throw new PeliculaEmpezadaException();
		}
		
		if (tarjeta.length() != 16) {
			throw new TarjetaInvalidaException();
		}

		// miramos si quedan suficientes butacas libres para la sesion
		if (sesion.get().getButacasLibres() - localidades < 0)
			throw new SalaLlenaException(sesion.get().getId(), sesion.get().getButacasLibres());

		// miramos que el cine donde se esta haciendo la compra
		// corresponde con el de la sesion
//		if (cineId != sesion.get().getSala().getCine().getId()) {
//			throw new InstanceNotFoundException("cine", cineId);
//		}

		Compra compra = new Compra(localidades, sesion.get(), user, tarjeta, LocalDateTime.now());

		sesion.get().setButacasLibres(sesion.get().getButacasLibres() - localidades);

		compraDao.save(compra);

		return compra;
	}

	@Override
	@Transactional(readOnly=true)
	public Block<Compra> findComprasByUser(Long userId, int page, int size) {

		Slice<Compra> slice = compraDao.findByUserIdOrderByFechaDesc(userId, PageRequest.of(page, size));

		return new Block<>(slice.getContent(), slice.hasNext());
	}

	@Override
	public void entregarEntradas(Long compraId, String tarjeta) throws InstanceNotFoundException,
			PeliculaEmpezadaException, TicketEntregadoException, TarjetaNoCorrespondeException {

		// Comprueba el id de compra y el número de tarjeta
		Optional<Compra> compraOptional = compraDao.findByIdAndTarjeta(compraId, tarjeta);

		if (!compraOptional.isPresent()) {
			throw new TarjetaNoCorrespondeException();
		}

		Compra compra = compraOptional.get();
		Sesion sesion = compra.getSesion();

		if (!LocalDateTime.now().isBefore(sesion.getHora())) {
			throw new PeliculaEmpezadaException();
		}

		if (compra.isVendida()) {
			throw new TicketEntregadoException(compra.getId());
		}

		compra.setVendida(true);
	}

}
