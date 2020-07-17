package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Cine;

public class CineConversor {

	private CineConversor() {}
	
	public final static CineDto toCineDto(Cine cine) {
		return new CineDto(cine.getId(), cine.getNombre());
	}
	
	public final static List<CineDto> toCineDtos(List<Cine> cines) {
		return cines.parallelStream().map(c -> toCineDto(c)).collect(Collectors.toList());
	}
}
