package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Ciudad;

public class CiudadConversor {
	
	private CiudadConversor() {}
	
	public final static CiudadDto toCiudadDto(Ciudad ciudad) {
		return new CiudadDto(ciudad.getId(), ciudad.getNombre());
	}
	
	public final static List<CiudadDto> toCiudadDtos(List<Ciudad> ciudades) {
		return ciudades.stream().map(c -> toCiudadDto(c)).collect(Collectors.toList());
	}
}
