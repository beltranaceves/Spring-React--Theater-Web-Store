package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Compra;

public class CompraConversor {

	private CompraConversor() {
	}

	public final static List<CompraDto> toCompraDtos(List<Compra> compras) {

		return compras.stream().map(o -> toCompraDto(o)).collect(Collectors.toList());
	}

	public final static CompraDto toCompraDto(Compra compra) {

		return new CompraDto(toMillis(compra.getFecha()), compra.getSesion().getSala().getCine().getNombre(),
				compra.getSesion().getPelicula().getTitulo(), compra.getLocalidades(), compra.getPrecioTotal(),
				toMillis(compra.getSesion().getHora()));
	}

	private final static long toMillis(LocalDateTime date) {
		return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}
}
