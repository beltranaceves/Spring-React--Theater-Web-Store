-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

-- Viewer User
INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('viewer', '$2a$10$UaIX1wXSdM58WtMqcF9LC.kjvNGQHACJdcpttgP9yiA/U6GBganJS', 'Viewer', 'Viewer', 'viewer@gmail.com', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('ticketseller', '$2a$10$/yEylyB3JUc5/2aV1rt.auPjHQl0D.3NjGDf0Xngkrmc0p0ArBUWC', 'Ticket', 'Seller', 'ticketseller@gmail.com', 1);

INSERT INTO Ciudad (nombre) VALUES ('A Coruna');
INSERT INTO Ciudad (nombre) VALUES ('Lugo');
INSERT INTO Ciudad (nombre) VALUES ('Ourense');

INSERT INTO Cine (nombre, ciudadId) VALUES ('Cinesa CC. Marineda',1);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Yelmo CC. Espacio Coruna',1);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Filmax Coruna',1);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Cines Yelmo Lugo',2);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Filmax Lugo',2);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Pictures Lugo Cines',2);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Cines Yelmo Ourense',3);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Pictures Orense Cines',3);
INSERT INTO Cine (nombre, ciudadId) VALUES ('Cines Gallegos Ourense',3);


INSERT INTO Pelicula (titulo, duracion, resumen) VALUES ('Fantasy Island',100,'Después de ganar un concurso, un diverso grupo de personas llega a Fantasy Island, una particular y paradisíaca isla que alberga un resort, regentado por Mr. Roarke (Michael Peña). Se trata de un lugar en el que todo es posible, y que ofrece a sus visitantes la posibilidad de vivir una fantasía relacionada con sus vidas, mediante una experiencia de inmersión diseñada a medida. Pero, pronto descubrirán que la isla no es lo que ellos creen, y se convertirá en su peor pesadilla.');
INSERT INTO Pelicula (titulo, duracion, resumen) VALUES ('1917',150,' Primera Guerra Mundial. A dos jóvenes soldados británicos les encomiendan una misión imposible: deben entregar un mensaje, en una carrera contrareloj, atravesando territorio enemigo. Su objetivo será evitar a toda costa un violento ataque. Si no llegan a tiempo, 1.600 soldados perderán la vida, entre ellos el hermano de uno de los dos jóvenes soldados.');
INSERT INTO Pelicula (titulo, duracion, resumen) VALUES ('Underwater',110,'Una instalación de investigación submarina a 11 mil metros bajo el mar. Allí se encuentra el equipo de investigadores acuáticos formado por Norah (Kristen Stewart), Paul (T.J. Miller), Rodrigo (Mamoudou Athie), Emily (Jessica Henwick) y el capitán (Vincent Cassel) de todos ellos. Después de ser devastados por un terremoto, la tripulación tiene que decidir cómo sobrevivir. Deciden caminar por el fondo del océano hasta llegar a una lejana estación. Pero a medida que avanzan se dan cuenta de que unos extraños depredadores marinos están decididos a matarles.');
INSERT INTO Pelicula (titulo, duracion, resumen) VALUES ('Jojo Rabit',90,'Jojo Rabbit es un niño viviendo en plena 2ª Guerra Mundial. Su único escape es su amigo imaginario, una versión de Hitler étnicamente incorrecta que incita los ciegos ideales patrióticos del niño. Todo esto cambia cuando descubre que su madre Rosie está escondiendo a una joven judía en su ático.');
INSERT INTO Pelicula (titulo, duracion, resumen) VALUES ('Aves de presa',100,'Alejándose del Escuadrón Suicida y de Joker, la villana de DC Harley Quinn (Margot Robbie) se alía con otras tres superheroínas para formar el grupo Birds of Prey. Además de Quinn, esta asociación está formada por Black Canary (Jurnee Smolett-Bell), Huntress (Mary Elizabeth Winstead) y Renee Montoya (Rosie Perez). Las cuatro heroínas trabajan juntas para tratar de salvar la vida de una joven, llamada Cassandra Cain (Ella Jay Basco), amenazada por un malvado criminal conocido como Máscara Negra (Ewan McGregor).');
INSERT INTO Pelicula (titulo, duracion, resumen) VALUES ('Mujercitas',130,'Massachusetts, mediados del siglo XIX. Las hermanas March, Meg (Emma Watson), Beth (Eliza Scanlen), Jo (Saoirse Ronan) y Amy (Florence Pugh), deberán enfrentarse al reto de llegar a la edad adulta con el conflicto bélico de la la Guerra Civil de fondo. Las cuatro hermanas trabarán amistad con el vecino de al lado, un joven llamado Laurie (Timothée Chalamet), que rápidamente se convertirá en amigo de la familia.');


INSERT INTO Sala (nombre, capacidad, cineId) VALUES ('Sala 1',5,1);
INSERT INTO Sala (nombre, capacidad, cineId) VALUES ('Sala 2',5,1);
INSERT INTO Sala (nombre, capacidad, cineId) VALUES ('Sala 3',5,1);
INSERT INTO Sala (nombre, capacidad, cineId) VALUES ('Sala 4',20,1);
INSERT INTO Sala (nombre, capacidad, cineId) VALUES ('Sala 5',20,1);
INSERT INTO Sala (nombre, capacidad, cineId) VALUES ('Sala 6',20,1);
INSERT INTO Sala (nombre, capacidad, cineId) VALUES ('Sala 1',20,4);

--Cine 1--

-- Dia 0
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),4.5,1,1,5); 
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 18:55' DAY_MINUTE),4.5,2,1,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),4.5,3,1,5);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),4.5,4,2,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 15:55' DAY_MINUTE),4.5,5,2,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),4.5,6,2,20);

-- Dia 1

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 00:05' DAY_MINUTE),4.5,6,1,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 18:55' DAY_MINUTE),4.5,5,1,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 23:55' DAY_MINUTE),4.5,4,1,5);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 00:05' DAY_MINUTE),4.5,3,6,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 18:55' DAY_MINUTE),4.5,2,6,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '1 23:55' DAY_MINUTE),4.5,1,6,20);

-- Dia 2

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 00:05' DAY_MINUTE),4.5,1,3,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 18:55' DAY_MINUTE),4.5,3,3,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 23:55' DAY_MINUTE),4.5,5,3,5);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 00:05' DAY_MINUTE),4.5,2,5,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 18:55' DAY_MINUTE),4.5,4,5,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '2 23:55' DAY_MINUTE),4.5,6,5,20);

-- Dia 3

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 00:05' DAY_MINUTE),4.5,2,5,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 18:55' DAY_MINUTE),4.5,2,5,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 23:55' DAY_MINUTE),4.5,2,5,20);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 00:05' DAY_MINUTE),4.5,5,2,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 18:55' DAY_MINUTE),4.5,5,2,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '3 23:55' DAY_MINUTE),4.5,5,2,5);

-- Dia 4

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 00:05' DAY_MINUTE),4.5,5,1,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 18:55' DAY_MINUTE),4.5,5,1,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 23:55' DAY_MINUTE),4.5,5,1,5);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 00:05' DAY_MINUTE),4.5,3,5,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 18:55' DAY_MINUTE),4.5,3,5,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '4 23:55' DAY_MINUTE),4.5,3,5,20);

-- Dia 5

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 00:05' DAY_MINUTE),4.5,6,4,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 18:55' DAY_MINUTE),4.5,6,4,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 23:55' DAY_MINUTE),4.5,6,4,20);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 00:05' DAY_MINUTE),4.5,2,2,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 18:55' DAY_MINUTE),4.5,2,2,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '5 23:55' DAY_MINUTE),4.5,2,2,5);

-- Dia 6

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 00:05' DAY_MINUTE),4.5,3,6,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 18:55' DAY_MINUTE),4.5,3,6,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 23:55' DAY_MINUTE),4.5,3,6,20);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 00:05' DAY_MINUTE),4.5,4,3,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 18:55' DAY_MINUTE),4.5,4,3,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '6 23:55' DAY_MINUTE),4.5,4,3,5);

-- Cine 4

-- Dia 0
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),4.5,1,7,5); 
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 18:55' DAY_MINUTE),4.5,2,7,5);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),4.5,3,7,5);

INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE),4.5,4,7,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 15:55' DAY_MINUTE),4.5,5,7,20);
INSERT INTO Sesion (hora, precio, peliculaId, salaId, butacasLibres) VALUES (DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE),4.5,6,7,20);