package es.udc.paproject.backend.model.entities;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CriticaDao extends PagingAndSortingRepository<Critica, Long> {
	
	Slice<Critica> findByPeliculaIdOrderByFechaDesc(Long peliculaId, Pageable pageable);
	
	List<Critica> findByPeliculaIdOrderByFecha(Long peliculaId);
	
	Optional<Critica> findByUserIdAndPeliculaId(Long userId, Long peliculaId);
	
}


