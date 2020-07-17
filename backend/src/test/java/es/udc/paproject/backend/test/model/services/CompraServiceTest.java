package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cine;
import es.udc.paproject.backend.model.entities.CineDao;
import es.udc.paproject.backend.model.entities.Ciudad;
import es.udc.paproject.backend.model.entities.CiudadDao;
import es.udc.paproject.backend.model.entities.Compra;
import es.udc.paproject.backend.model.entities.CompraDao;
import es.udc.paproject.backend.model.entities.Pelicula;
import es.udc.paproject.backend.model.entities.PeliculaDao;
import es.udc.paproject.backend.model.entities.Sala;
import es.udc.paproject.backend.model.entities.SalaDao;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.entities.SesionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.UserDao;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.CompraService;
import es.udc.paproject.backend.model.services.UserService;

import es.udc.paproject.backend.model.exceptions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CompraServiceTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PeliculaDao peliculaDao;

	@Autowired
	private SesionDao sesionDao;

	@Autowired
	private SalaDao salaDao;

	@Autowired
	private CineDao cineDao;

	@Autowired
	private CiudadDao ciudadDao;

	@Autowired
	private CompraDao compraDao;

	@Autowired
	private CompraService compraService;

	@Autowired
	private UserService userService;

	private User signUpUser(String userName) {

		User user = new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");

		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}

		return user;

	}

	private Pelicula addPelicula(String titulo, String descripcion) {
		return peliculaDao.save(new Pelicula(titulo, 120, descripcion));
	}

	private Pelicula addPelicula(String titulo) {
		return addPelicula(titulo, "descripcion");
	}

	private Cine addCine(String nombre) {
		Ciudad ciudad = new Ciudad("A Coruna");
		ciudadDao.save(ciudad);
		return cineDao.save(new Cine(nombre, ciudad));
	}

	@Test
	public void testComprarTickets()
			throws InstanceNotFoundException, SalaLlenaException, PeliculaEmpezadaException, TarjetaInvalidaException {

		User user = signUpUser("user");
		Ciudad ciudad = new Ciudad("Lugo");
		ciudadDao.save(ciudad);
		Cine cine = new Cine("Cinesa", ciudad);
		cineDao.save(cine);
		Sala sala = new Sala("Sala 1", 50, cine);
		salaDao.save(sala);
		Pelicula pelicula = addPelicula("Star Wars");
		Sesion sesion = new Sesion(LocalDateTime.now().plusHours(2), new BigDecimal(3), pelicula, sala,
				sala.getCapacidad());
		sesionDao.save(sesion);

		Compra compraExpected = new Compra(10, sesion, user, "1234567890123456", LocalDateTime.now());
		Compra compra = compraService.buy(user.getId(), sesion.getId(), 10, "1234567890123456");

		assertEquals(compraExpected.getLocalidades(), compra.getLocalidades());
		assertEquals(compraExpected.getSesion(), compra.getSesion());
		assertEquals(compraExpected.getUser(), compra.getUser());
		assertEquals(compraExpected.getTarjeta(), compra.getTarjeta());
		assertEquals(false, compra.isVendida());
		assertEquals(40, sesion.getButacasLibres());
		assertEquals(compraExpected.getPrecioTotal(), compraExpected.getPrecioTotal());

	}

	@Test
	public void testComprarTicketsTarjetaInvalida()
			throws InstanceNotFoundException, SalaLlenaException, PeliculaEmpezadaException, TarjetaInvalidaException {

		User user = signUpUser("user");
		Ciudad ciudad = new Ciudad("Lugo");
		ciudadDao.save(ciudad);
		Cine cine = new Cine("Cinesa", ciudad);
		cineDao.save(cine);
		Sala sala = new Sala("Sala 1", 50, cine);
		salaDao.save(sala);
		Pelicula pelicula = addPelicula("Star Wars");
		Sesion sesion = new Sesion(LocalDateTime.now().plusHours(2), new BigDecimal(3), pelicula, sala,
				sala.getCapacidad());
		sesionDao.save(sesion);

		assertThrows(TarjetaInvalidaException.class, () -> compraService.buy(user.getId(), sesion.getId(), 10, "1"));
	}

	@Test
	public void testEntregarEntradas() throws InstanceNotFoundException, SalaLlenaException, PeliculaEmpezadaException,
			TicketEntregadoException, TarjetaNoCorrespondeException {

		// comprobar si devuelve una excepcion en vez
		// de un false.

		User user = signUpUser("user");
		Ciudad ciudad = new Ciudad("Lugo");
		ciudadDao.save(ciudad);
		Cine cine = new Cine("Cinesa", ciudad);
		cineDao.save(cine);
		Sala sala = new Sala("Sala 1", 50, cine);
		salaDao.save(sala);
		Pelicula pelicula = addPelicula("Star Wars");

		Sesion sesion = new Sesion(LocalDateTime.now().plusHours(2), new BigDecimal(3.5), pelicula, sala,
				sala.getCapacidad());

		sesionDao.save(sesion);

		Compra compra = compraDao.save(new Compra(10, sesion, user, "123456789", LocalDateTime.now()));

		compraService.entregarEntradas(compra.getId(), "123456789");
		assertEquals(true, compra.isVendida());
	}

	@Test
	public void testEntregarEntradasPeliculaEmpezada()
			throws InstanceNotFoundException, SalaLlenaException, PeliculaEmpezadaException, TicketEntregadoException {

		// comprobar si devuelve una excepcion en vez
		// de un false.

		User user = signUpUser("user");
		Ciudad ciudad = new Ciudad("Lugo");
		ciudadDao.save(ciudad);
		Cine cine = new Cine("Cinesa", ciudad);
		cineDao.save(cine);
		Sala sala = new Sala("Sala 1", 50, cine);
		salaDao.save(sala);
		Pelicula pelicula = addPelicula("Star Wars");

		Sesion sesion = new Sesion(LocalDateTime.now().minusHours(2), new BigDecimal(3.5), pelicula, sala,
				sala.getCapacidad());

		sesionDao.save(sesion);

		Long idCompra = compraDao.save(new Compra(10, sesion, user, "123456789", LocalDateTime.now())).getId();

		assertThrows(PeliculaEmpezadaException.class, () -> compraService.entregarEntradas(idCompra, "123456789"));
	}

	@Test
	public void testEntregarEntradasTicketEntregado()
			throws InstanceNotFoundException, SalaLlenaException, PeliculaEmpezadaException, TicketEntregadoException {

		// comprobar si devuelve una excepcion en vez
		// de un false.

		User user = signUpUser("user");
		Ciudad ciudad = new Ciudad("Lugo");
		ciudadDao.save(ciudad);
		Cine cine = new Cine("Cinesa", ciudad);
		cineDao.save(cine);
		Sala sala = new Sala("Sala 1", 50, cine);
		salaDao.save(sala);
		Pelicula pelicula = addPelicula("Star Wars");

		Sesion sesion = new Sesion(LocalDateTime.now().plusHours(2), new BigDecimal(3.5), pelicula, sala,
				sala.getCapacidad());

		sesionDao.save(sesion);

		Compra compra = new Compra(10, sesion, user, "123456789", LocalDateTime.now());

		compraDao.save(compra);

		compra.setVendida(true);

		assertThrows(TicketEntregadoException.class, () -> compraService.entregarEntradas(compra.getId(), "123456789"));
	}

	@Test
	public void testFindNoCompras() {

		User user = signUpUser("User");
		Block<Compra> expectedCompras = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedCompras, compraService.findComprasByUser(user.getId(), 0, 1));
	}

	@Test
	public void TestFindComprasByUser() {

		User user = signUpUser("User");
		userDao.save(user);

		Cine cine = addCine("Cinesa");

		Sala sala = new Sala("Sala 1", 50, cine);
		salaDao.save(sala);
		Pelicula pelicula = addPelicula("Star Wars");

		Sesion sesion1 = new Sesion(LocalDateTime.of(2018, 12, 1, 10, 2, 3).plusDays(2), new BigDecimal(3), pelicula,
				sala, sala.getCapacidad());
		sesionDao.save(sesion1);

		Sesion sesion2 = new Sesion(LocalDateTime.of(2018, 12, 2, 10, 2, 3).plusDays(3), new BigDecimal(3), pelicula,
				sala, sala.getCapacidad());
		sesionDao.save(sesion2);

		Compra compra1 = new Compra(10, sesion1, user, "123456789", LocalDateTime.of(2017, 10, 1, 10, 2, 3));
		compraDao.save(compra1);
		Compra compra2 = new Compra(5, sesion2, user, "123456789", LocalDateTime.of(2018, 11, 1, 10, 2, 3));
		compraDao.save(compra2);
		Compra compra3 = new Compra(2, sesion1, user, "123456789", LocalDateTime.of(2018, 12, 1, 10, 2, 3));
		compraDao.save(compra3);

		Block<Compra> expectedBlock = new Block<>(Arrays.asList(compra3, compra2), true);
		assertEquals(expectedBlock, compraService.findComprasByUser(user.getId(), 0, 2));

		expectedBlock = new Block<>(Arrays.asList(compra1), false);
		assertEquals(expectedBlock, compraService.findComprasByUser(user.getId(), 1, 2));

	}
}
