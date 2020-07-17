package es.udc.paproject.backend.model.services;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import es.udc.paproject.backend.model.entities.CarteleraItemDto;
import es.udc.paproject.backend.model.entities.Cine;
import es.udc.paproject.backend.model.entities.CineDao;
import es.udc.paproject.backend.model.entities.Ciudad;
import es.udc.paproject.backend.model.entities.CiudadDao;
import es.udc.paproject.backend.model.entities.Critica;
import es.udc.paproject.backend.model.entities.CriticaDao;
import es.udc.paproject.backend.model.entities.Pelicula;
import es.udc.paproject.backend.model.entities.PeliculaDao;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.entities.SesionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.FechaNoValidaException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.InvalidParameterException;
import es.udc.paproject.backend.model.exceptions.PeliculaEmpezadaException;
@Service
@Transactional(readOnly = false)
public class CarteleraServiceImpl implements CarteleraService {

	@Autowired
	private CiudadDao ciudadDao;

	@Autowired
	private CineDao cineDao;

	@Autowired
	private SesionDao sesionDao;

	@Autowired
	private PeliculaDao peliculaDao;
	
	@Autowired
	private CriticaDao criticaDao;

	@Autowired
	private PermissionChecker permissionChecker;
	
	@Override
	public List<Ciudad> findAllCiudades() {

		Iterable<Ciudad> ciudades = ciudadDao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
		List<Ciudad> ciudadesAsList = new ArrayList<>();

		ciudades.forEach(c -> ciudadesAsList.add(c));

		return ciudadesAsList;
	}

	@Override
	public List<Cine> findCinesByCiudad(Long ciudadId) {

		List<Cine> cines = cineDao.findByCiudadIdOrderByNombre(ciudadId);

		return cines;
	}

	@Override
	public Pelicula findPelicula(Long id) throws InstanceNotFoundException {

		Optional<Pelicula> pelicula = peliculaDao.findById(id);

		if (!pelicula.isPresent()) {
			throw new InstanceNotFoundException("project.entities.pelicula", id);
		}

		return pelicula.get();
	}

	@Override
	public Sesion findSesion(Long sesionId) throws InstanceNotFoundException, PeliculaEmpezadaException {

		Optional<Sesion> sesion = sesionDao.findById(sesionId);

		if (!sesion.isPresent()) {
			throw new InstanceNotFoundException("Sesion ", sesionId);
		}

		if (sesion.get().getHora().isBefore(LocalDateTime.now())) {
			throw new PeliculaEmpezadaException();
		}

		return sesion.get();
	}

	@Override
	public List<CarteleraItemDto> showCartelera(Long cineId, LocalDateTime fecha) throws FechaNoValidaException {

		List<CarteleraItemDto> dtoPelis = new ArrayList<>();
		CarteleraItemDto item = new CarteleraItemDto();

		if (fecha.isBefore(LocalDateTime.now().withSecond(0)) || fecha.isAfter(LocalDateTime.now().plusDays(6)))
			throw new FechaNoValidaException();

		List<Sesion> sesiones = sesionDao.findBySalaCineIdAndHoraBetweenOrderByPeliculaTituloAscHoraAsc(cineId, fecha,
				fecha.withHour(23).withMinute(59));

		for (Sesion sesion : sesiones) {
			if (sesion.getPelicula() != item.getPelicula()) {
				item.setPelicula(sesion.getPelicula());
				item.setSesion(sesion);
				dtoPelis.add(new CarteleraItemDto(item.getPelicula(), item.getSesiones()));
			} else
				item.getSesiones().add(sesion);
		}
		return dtoPelis;
	}

	@Override
	public List<Critica>  findCriticasByPeliculaOrderByFecha(Long peliculaId) {
		List<Critica> criticas = criticaDao.findByPeliculaIdOrderByFecha(peliculaId);

		return criticas;
	}

	
	@Override
	public Block<Critica> findCriticasByPeliculaOrderByFecha(Long peliculaId, int page, int size) {
		
		Slice<Critica> slice = criticaDao.findByPeliculaIdOrderByFechaDesc(peliculaId, PageRequest.of(page, size));

		return new Block<>(slice.getContent(), slice.hasNext());
	}

	@Override
	public Critica publicarCritica(Long peliculaId, Long userId, int puntuacion, String titulo, String comentario)
			throws DuplicateInstanceException, InstanceNotFoundException, InvalidParameterException{
		User user = permissionChecker.checkUser(userId);
		Optional<Pelicula> pelicula= peliculaDao.findById(peliculaId);
		Optional<Critica> critica= criticaDao.findByUserIdAndPeliculaId(userId, peliculaId);

		if (!pelicula.isPresent()) {
			throw new InstanceNotFoundException("sesion", peliculaId);
		}
		
		if (critica.isPresent()) {
			throw new DuplicateInstanceException("una valoracón a su nombre para esta película. Código original:", peliculaId);
		}
		
		
		if (titulo == null) {
			throw new InvalidParameterException("El título no puede ser nulo", titulo);
		}
		
		if (comentario == null) {
			throw new InvalidParameterException("El comentario no puede ser nulo", comentario);
		}
		
		
		Critica critica_insert = new Critica(puntuacion, titulo, comentario, user, pelicula.get(), LocalDateTime.now());

		criticaDao.save(critica_insert);
		
		return critica_insert;
		
	}


}
