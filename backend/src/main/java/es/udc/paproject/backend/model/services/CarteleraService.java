package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.List;

import es.udc.paproject.backend.model.entities.CarteleraItemDto;
import es.udc.paproject.backend.model.entities.Cine;
import es.udc.paproject.backend.model.entities.Ciudad;
import es.udc.paproject.backend.model.entities.Pelicula;
import es.udc.paproject.backend.model.entities.Critica;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.exceptions.FechaNoValidaException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.InvalidParameterException;
import es.udc.paproject.backend.model.exceptions.PeliculaEmpezadaException;

public interface CarteleraService {

	List<Ciudad> findAllCiudades();

	List<Cine> findCinesByCiudad(Long ciudadId);

	Pelicula findPelicula(Long id) throws InstanceNotFoundException;
	
	List<Critica> findCriticasByPeliculaOrderByFecha(Long peliculaId);
	
	Block<Critica> findCriticasByPeliculaOrderByFecha(Long peliculaId, int page, int size);
	
	Critica publicarCritica(Long peliculaId, Long userId, int puntuacion, String titulo, String comentario) throws InvalidParameterException, DuplicateInstanceException,  InstanceNotFoundException;
	
	Sesion findSesion(Long sesionId) throws InstanceNotFoundException, PeliculaEmpezadaException;

	List<CarteleraItemDto> showCartelera(Long cineId, LocalDateTime fecha)
			throws FechaNoValidaException;

}
