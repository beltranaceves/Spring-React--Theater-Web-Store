package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.CompraConversor.toCompraDtos;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.entities.Compra;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PeliculaEmpezadaException;
import es.udc.paproject.backend.model.exceptions.SalaLlenaException;
import es.udc.paproject.backend.model.exceptions.TarjetaInvalidaException;
import es.udc.paproject.backend.model.exceptions.TarjetaNoCorrespondeException;
import es.udc.paproject.backend.model.exceptions.TicketEntregadoException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.CompraService;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.BuyParamsDto;
import es.udc.paproject.backend.rest.dtos.CompraDto;
import es.udc.paproject.backend.rest.dtos.CreditCardDto;
import es.udc.paproject.backend.rest.dtos.IdDto;
import es.udc.paproject.backend.rest.common.ErrorsDto;

@RestController

@RequestMapping("/compras")

public class CompraController {

	// SOBRA private final static String MAX_CANTIDAD_SOBREPASADA_CODE = "project.exceptions.MaxCantidadSobrepasada";
	private final static String SALA_LLENA_CODE = "project.exceptions.SalaLlenaException";
	private final static String TICKET_ENTREGADO_CODE = "project.exceptions.TicketEntregadoException";
	private final static String TARJETA_NO_CORRESPONDE_CODE= "project.exceptions.TarjetaNoCorrespondeException";
	private final static String TARJETA_INVALIDA_CODE = "project.exceptions.TarjetaInvalidaException";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CompraService compraService;

	@ExceptionHandler(TarjetaInvalidaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleTarjetaInvalidaException(TarjetaInvalidaException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(TARJETA_INVALIDA_CODE, null,
				TARJETA_INVALIDA_CODE, locale);

		return new ErrorsDto(errorMessage);
	}
	
	@ExceptionHandler(SalaLlenaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleMaxSalaLlenaException(SalaLlenaException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(SALA_LLENA_CODE,
				new Object[] { exception.getEntradas() }, SALA_LLENA_CODE, locale);

		return new ErrorsDto(errorMessage);
	}

	@ExceptionHandler(TicketEntregadoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleTicketEntregadoException(TicketEntregadoException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(TICKET_ENTREGADO_CODE, new Object[] { exception.getCompraId() },
				TICKET_ENTREGADO_CODE, locale);

		return new ErrorsDto(errorMessage);
	}
	
	@ExceptionHandler(TarjetaNoCorrespondeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleTarjetaNoCorrespondeException(TarjetaNoCorrespondeException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(TARJETA_NO_CORRESPONDE_CODE, null,
				TARJETA_NO_CORRESPONDE_CODE, locale);

		return new ErrorsDto(errorMessage);
	}
	
	@PostMapping("/buy")
	public IdDto buy(@RequestAttribute Long userId,
			@Validated @RequestBody BuyParamsDto params)
			throws InstanceNotFoundException, SalaLlenaException, PeliculaEmpezadaException, TarjetaInvalidaException {

		return new IdDto(compraService
				.buy(userId, params.getSesionId(), params.getQuantity(), params.getCreditCard()).getId());
	}
	
	@GetMapping
	public BlockDto<CompraDto> findComprasByUser(@RequestAttribute @RequestParam Long userId,
			@RequestParam(defaultValue = "0") int page) {

		Block<Compra> compraBlock = compraService.findComprasByUser(userId, page, 10);

		return new BlockDto<>(toCompraDtos(compraBlock.getItems()), compraBlock.getExistMoreItems());
	}
	
	@PostMapping("{compraId}/entregarTicket")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregarEntradas(@PathVariable Long compraId, @RequestBody CreditCardDto creditCard)
			throws InstanceNotFoundException, PeliculaEmpezadaException, TicketEntregadoException, TarjetaNoCorrespondeException {
		compraService.entregarEntradas(compraId, creditCard.getCreditCard());
	}
}
