package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.CarteleraConversor.toCarteleraDtos;
import static es.udc.paproject.backend.rest.dtos.CriticaConversor.toCriticaDtos;
import static es.udc.paproject.backend.rest.dtos.CineConversor.toCineDtos;
import static es.udc.paproject.backend.rest.dtos.CiudadConversor.toCiudadDtos;
import static es.udc.paproject.backend.rest.dtos.PeliculaConversor.toPeliculaDto;
import static es.udc.paproject.backend.rest.dtos.SesionConversor.toSesionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.exceptions.FechaNoValidaException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PeliculaEmpezadaException;
import es.udc.paproject.backend.model.entities.Critica;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.InvalidParameterException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.CarteleraService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.CarteleraDto;
import es.udc.paproject.backend.rest.dtos.CineDto;
import es.udc.paproject.backend.rest.dtos.CiudadDto;
import es.udc.paproject.backend.rest.dtos.IdDto;
import es.udc.paproject.backend.rest.dtos.PeliculaDto;
import es.udc.paproject.backend.rest.dtos.SesionDto;
import es.udc.paproject.backend.rest.dtos.PublishParamsDto;
import es.udc.paproject.backend.rest.dtos.CriticaDto;

@RestController
@RequestMapping("/cartelera")
public class CarteleraController {
	
	private final static String FECHA_NO_VALIDA_CODE = "project.exceptions.FechaNoValidaException";
	private final static String PARAMETRO_NO_VALIDO_CODE = "project.exceptions.InvalidParameterException";
	private final int pageSize= 1;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CarteleraService carteleraService;
	
	@ExceptionHandler(FechaNoValidaException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorsDto handleFechaNoValidaException(FechaNoValidaException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(FECHA_NO_VALIDA_CODE, new Object[] {exception.getMessage()}, FECHA_NO_VALIDA_CODE, locale);

		return new ErrorsDto(errorMessage);
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorsDto handleInvalidParameterException(InvalidParameterException exception, Locale locale, String param_no_valido) {

		String errorMessage = messageSource.getMessage(PARAMETRO_NO_VALIDO_CODE, new Object[] {exception.getMessage()}, PARAMETRO_NO_VALIDO_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@GetMapping("/ciudades")
	List<CiudadDto> findAllCiudades() {
		return toCiudadDtos(carteleraService.findAllCiudades());
	}

	 @GetMapping("/ciudades/{id}/cines")
	 List<CineDto> findCinesByCiudad(@PathVariable Long id) {
		 return toCineDtos(carteleraService.findCinesByCiudad(id));
	}
	
	@GetMapping("/peliculas/{id}")
	PeliculaDto findPelicula(@PathVariable Long id) throws InstanceNotFoundException {
		List<Critica> criticas = carteleraService.findCriticasByPeliculaOrderByFecha(id);
		Double counter = 0.0;
		Double valoracion = 0.0;
		int numeroCriticas = criticas.size();
		for (int i  = 0; i < numeroCriticas; i++) {
			counter += criticas.get(i).getPuntuacion();
		}

		if (numeroCriticas > 0) {
			valoracion = counter/criticas.size();
		}
		
		return toPeliculaDto(carteleraService.findPelicula(id), valoracion, numeroCriticas);
	}
	
	@GetMapping("/sesiones/{id}")
	SesionDto findSesion(@PathVariable Long id) throws InstanceNotFoundException, PeliculaEmpezadaException {
		return toSesionDto(carteleraService.findSesion(id));
	}
	
	@GetMapping("/show")
	List<CarteleraDto> showCartelera(@RequestParam(required=true) Long cineId, 
			@RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
			throws FechaNoValidaException {
		
		return toCarteleraDtos(carteleraService.showCartelera(cineId, date == null ? LocalDateTime.now() : date.atStartOfDay()));
	}
	
	@PostMapping("/publish")
	public IdDto publishReview(@RequestAttribute Long userId, 
			@Validated @RequestBody PublishParamsDto params)
			throws InstanceNotFoundException, DuplicateInstanceException, InvalidParameterException {
		
		return new IdDto(carteleraService.publicarCritica(params.getPeliculaId(), userId, params.getPuntuacion(), params.getTitulo(), params.getComentario()).getId());
	}

	@GetMapping("/criticas")
	public BlockDto<CriticaDto> findCriticasByPelicula(@RequestParam Long peliculaId, 
			@RequestParam(defaultValue = "0") int page) throws InstanceNotFoundException {
		
		Block<Critica> criticaBlock = carteleraService.findCriticasByPeliculaOrderByFecha(peliculaId, page, pageSize);
		
		return new BlockDto<>(toCriticaDtos(criticaBlock.getItems()), criticaBlock.getExistMoreItems());
	}
	
}
