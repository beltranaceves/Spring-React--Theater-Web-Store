-- Indexes for primary keys have been explicitly created.
DROP TABLE Compra;
DROP TABLE Sesion;
DROP TABLE Sala;
DROP TABLE Critica;
DROP TABLE Pelicula;
DROP TABLE User;
DROP TABLE Cine;
DROP TABLE Ciudad;
-- -----------------------------------------------------
-- Table Ciudad
-- -----------------------------------------------------
CREATE TABLE Ciudad
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR (60) NOT NULL,
   CONSTRAINT CiudadPK PRIMARY KEY (id),
   CONSTRAINT CiudadNombreUniqueKey UNIQUE (nombre)
)
ENGINE = InnoDB;
--CREATE INDEX CiudadIndexByNombre ON Ciudad (nombre);
-- -----------------------------------------------------
-- Table Cine
-- -----------------------------------------------------
CREATE TABLE Cine
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR (60) NOT NULL,
   ciudadId BIGINT NOT NULL,
   CONSTRAINT CinePK PRIMARY KEY (id),
   CONSTRAINT CineCiudadIdFK FOREIGN KEY (ciudadId) REFERENCES Ciudad (id)
)
ENGINE = InnoDB;
--CREATE INDEX CineIndexByNombre ON Cine (nombre);
-- -----------------------------------------------------
-- Table User
-- -----------------------------------------------------
CREATE TABLE User
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   userName VARCHAR (60) COLLATE latin1_bin NOT NULL,
   password VARCHAR (60) NOT NULL,
   firstName VARCHAR (60) NOT NULL,
   lastName VARCHAR (60) NOT NULL,
   email VARCHAR (60) NOT NULL,
   role TINYINT NOT NULL,
   CONSTRAINT UserPK PRIMARY KEY (id),
   CONSTRAINT UserNameUniqueKey UNIQUE (userName)
)
ENGINE = InnoDB;
--CREATE INDEX UserIndexByUserName ON User (userName);
-- -----------------------------------------------------
-- Table Pelicula
-- -----------------------------------------------------
CREATE TABLE Pelicula
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   titulo VARCHAR (60) NOT NULL,
   duracion INTEGER NOT NULL,
   resumen VARCHAR (1024) NOT NULL,
   CONSTRAINT PeliculaPK PRIMARY KEY (id)
)
ENGINE = InnoDB;
--CREATE INDEX PeliculaIndexByTitulo ON Pelicula (titulo);
-- -----------------------------------------------------
-- Table Critica
-- -----------------------------------------------------
CREATE TABLE Critica 
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(60) NOT NULL,
  puntuacion INTEGER NOT NULL,
  comentario VARCHAR(1024) NOT NULL,
  peliculaId BIGINT NOT NULL,
  userId BIGINT NOT NULL,
  fecha DATETIME NOT NULL,
  CONSTRAINT CriticaPK PRIMARY KEY (id),
  CONSTRAINT CriticaPeliculaIdFK FOREIGN KEY (peliculaId) REFERENCES Pelicula (id),
  CONSTRAINT CriticaUserIdFK FOREIGN KEY (userId) REFERENCES User (id)
)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table Sala
-- -----------------------------------------------------
CREATE TABLE Sala
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR (60) NOT NULL,
   capacidad INTEGER NOT NULL,
   cineId BIGINT NOT NULL,
   CONSTRAINT SalaFK PRIMARY KEY (id),
   CONSTRAINT SalaCineIdFK FOREIGN KEY (cineId) REFERENCES Cine (id)
)
ENGINE = InnoDB;
--CREATE INDEX SalaIndexByNombre ON Sala (nombre);
-- -----------------------------------------------------
-- Table Sesion
-- -----------------------------------------------------
CREATE TABLE Sesion
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   hora DATETIME NOT NULL,
   precio DECIMAL
   (
      11,
      2
   )
   NOT NULL,
   peliculaId BIGINT NOT NULL,
   salaId BIGINT NOT NULL,
   butacasLibres INTEGER NOT NULL,
   version BIGINT DEFAULT 0,
   CONSTRAINT SesionPK PRIMARY KEY (id),
   CONSTRAINT SesionPeliculaIdFK FOREIGN KEY (peliculaId) REFERENCES Pelicula (id),
   CONSTRAINT SesionSalaIdFK FOREIGN KEY (salaId) REFERENCES Sala (id)
)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table Compra
-- -----------------------------------------------------
CREATE TABLE Compra
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   localidades INTEGER NOT NULL,
   sesionId BIGINT NOT NULL,
   userId BIGINT NOT NULL,
   tarjeta VARCHAR (60) NOT NULL,
   fecha DATETIME NOT NULL,
   vendida BOOLEAN,
   CONSTRAINT CompraId PRIMARY KEY (id),
   CONSTRAINT CompraSesionIdFK FOREIGN KEY (sesionId) REFERENCES Sesion (id),
   CONSTRAINT CompraUsuarioIdFK FOREIGN KEY (userId) REFERENCES User (id)
)
ENGINE = InnoDB;