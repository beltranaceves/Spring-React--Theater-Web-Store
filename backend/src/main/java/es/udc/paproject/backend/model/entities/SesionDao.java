package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SesionDao extends PagingAndSortingRepository<Sesion, Long> {

	List<Sesion> findBySalaCineIdAndHoraBetweenOrderByPeliculaTituloAscHoraAsc(Long cineId, LocalDateTime fecha1, LocalDateTime fecha2);
}
