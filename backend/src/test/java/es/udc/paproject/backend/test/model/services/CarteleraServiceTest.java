package es.udc.paproject.backend.test.model.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.services.CarteleraService;
import es.udc.paproject.backend.model.entities.Ciudad;
import es.udc.paproject.backend.model.entities.CiudadDao;
import es.udc.paproject.backend.model.entities.Pelicula;
import es.udc.paproject.backend.model.entities.PeliculaDao;
import es.udc.paproject.backend.model.entities.Sala;
import es.udc.paproject.backend.model.entities.SalaDao;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.entities.SesionDao;
import es.udc.paproject.backend.model.exceptions.FechaNoValidaException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PeliculaEmpezadaException;
import es.udc.paproject.backend.model.entities.CarteleraItemDto;
import es.udc.paproject.backend.model.entities.Cine;
import es.udc.paproject.backend.model.entities.CineDao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CarteleraServiceTest {

	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private CarteleraService carteleraService;

	@Autowired
	private CiudadDao ciudadDao;

	@Autowired
	private CineDao cineDao;

	@Autowired
	private PeliculaDao peliculaDao;

	@Autowired
	private SalaDao salaDao;

	@Autowired
	private SesionDao sesionDao;

	private Pelicula addPelicula(String titulo, String descripcion) {
		return peliculaDao.save(new Pelicula(titulo, 120, descripcion));
	}

	private Pelicula addPelicula(String titulo) {
		return addPelicula(titulo, "descripcion");
	}

	private Sesion addSesion(LocalDateTime fecha, Pelicula pelicula, Sala sala) {
		return sesionDao.save(new Sesion(fecha, new BigDecimal(4.50), pelicula, sala, sala.getCapacidad()));
	}

	private Sala addSala(String nombre, int capacidad, Cine cine) {
		return salaDao.save(new Sala(nombre, capacidad, cine));
	}

	private Cine addCine(String nombre) {
		Ciudad ciudad = new Ciudad("A Coruna");
		ciudadDao.save(ciudad);
		return cineDao.save(new Cine(nombre, ciudad));
	}

	private Cine addCine(String nombre, Ciudad ciudad) {
		return cineDao.save(new Cine(nombre, ciudad));
	}

	@Test
	public void testShowAllCiudades() {

		Ciudad ciudad1 = new Ciudad("A Coruna");
		Ciudad ciudad2 = new Ciudad("Lugo");

		ciudadDao.save(ciudad1);
		ciudadDao.save(ciudad2);

		List<Ciudad> ciudades = carteleraService.findAllCiudades();

		assertEquals(Arrays.asList(ciudad1, ciudad2), ciudades);
	}

	@Test
	public void testFindCinesByCiudad() {

		Ciudad ciudad1 = new Ciudad("A Coruna");
		ciudadDao.save(ciudad1);

		Cine cine1 = addCine("Yelmo", ciudad1);
		Cine cine2 = addCine("Cinesa", ciudad1);

		List<Cine> cines = carteleraService.findCinesByCiudad(ciudad1.getId());

		assertEquals(Arrays.asList(cine2, cine1), cines);

	}

	@Test
	public void testFindSesionById() throws InstanceNotFoundException, PeliculaEmpezadaException {

		Cine cine = addCine("cine");
		Sala sala = addSala("sala", 50, cine);
		Pelicula peli = addPelicula("peli");

		Sesion sesion = addSesion(LocalDateTime.now().plusDays(2), peli, sala);
		assertEquals(sesion, carteleraService.findSesion(sesion.getId()));
	}

	@Test
	public void testFindSesionByNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () -> carteleraService.findSesion(NON_EXISTENT_ID));
	}

	@Test
	public void testFindStartedSesion() {
		Cine cine = addCine("cine");
		Sala sala = addSala("sala", 50, cine);
		Pelicula peli = addPelicula("peli");
		Sesion sesion = addSesion(LocalDateTime.now().minusHours(3), peli, sala);

		assertThrows(PeliculaEmpezadaException.class, () -> carteleraService.findSesion(sesion.getId()));
	}

	@Test
	public void testFindPelicula() throws InstanceNotFoundException {
		Pelicula peli = addPelicula("Peli1", "sinopsis1");
		assertEquals(peli, carteleraService.findPelicula(peli.getId()));
	}

	@Test
	public void testFindPeliculaByNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () -> carteleraService.findPelicula(NON_EXISTENT_ID));
	}

	@Test
	public void testShowCartelera() throws FechaNoValidaException {

		Cine cine1 = addCine("Yelmo Coruna");

		Sala sala1 = addSala("sala1", 50, cine1);
		Sala sala2 = addSala("sala2", 50, cine1);

		Pelicula peli1 = addPelicula("Peli1");
		Pelicula peli2 = addPelicula("Peli2");
		
		LocalDateTime fecha1 = LocalDateTime.now().plusDays(2).withHour(23).withMinute(30);		

		Sesion sesion1 = addSesion(fecha1, peli1, sala1);
		Sesion sesion2 = addSesion(fecha1, peli2, sala2);

		CarteleraItemDto itemPeliDto1 = new CarteleraItemDto(peli1, Arrays.asList(sesion1));
		CarteleraItemDto itemPeliDto2 = new CarteleraItemDto(peli2, Arrays.asList(sesion2));

		List<CarteleraItemDto> itemList = carteleraService.showCartelera(cine1.getId(), LocalDateTime.now().plusDays(2));

		assertEquals(2, itemList.size());
		assertEquals(itemPeliDto1.getPelicula(), itemList.get(0).getPelicula());
		assertEquals(itemPeliDto1.getSesiones(), itemList.get(0).getSesiones());
		
		assertEquals(itemPeliDto2.getPelicula(), itemList.get(1).getPelicula());
		assertEquals(itemPeliDto2.getSesiones(), itemList.get(1).getSesiones());

	}
	

	@Test
	public void testShowCarteleraPastDate() {
		assertThrows(FechaNoValidaException.class,
				() -> carteleraService.showCartelera(addCine("cine").getId(), LocalDateTime.now().minusHours(2)));
	}

	@Test
	public void testShowCarteleraNextWeekDate() {
		assertThrows(FechaNoValidaException.class,
				() -> carteleraService.showCartelera(addCine("cine").getId(), LocalDateTime.now().plusDays(7)));
	}
}
