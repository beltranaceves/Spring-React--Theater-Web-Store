package es.udc.paproject.backend.model.entities;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CineDao extends PagingAndSortingRepository<Cine, Long> {
	
	List<Cine> findByCiudadIdOrderByNombre(Long ciudadId);
}

