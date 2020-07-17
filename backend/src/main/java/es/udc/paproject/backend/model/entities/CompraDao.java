package es.udc.paproject.backend.model.entities;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompraDao extends PagingAndSortingRepository<Compra, Long>{
	
	Slice<Compra> findByUserIdOrderByFechaDesc(Long userId, Pageable pageable);
	
	Optional<Compra> findByIdAndTarjeta(Long id, String tarjeta);
}
