-- CREAMOS LA BASE DE DATOS
CREATE SCHEMA `tienda_libros_db` ;

-- Usamos la Base de datos Creada
USE tienda_libros_db

-- CREAMOS LAS TABLAS EN LA BASE DE DATOS
CREATE TABLE `tienda_libros_db`.`libro` (
  `idlibro` INT NOT NULL AUTO_INCREMENT,
  `nombre_libro` VARCHAR(45) NULL,
  `autor` VARCHAR(45) NULL,
  `existencias` INT NULL,
  `precio` DOUBLE NULL,
  PRIMARY KEY (`idlibro`));

-- ORDEN CORRECTO DE LAS TABLAS
ALTER TABLE `tienda_libros_db`.`libro`
CHANGE COLUMN `nombre_libro` `nombre_libro` VARCHAR(255) NULL DEFAULT NULL AFTER `id_libro`,
CHANGE COLUMN `precio` `precio` DOUBLE NOT NULL AFTER `autor`;


-- Agregar datos a la Base de datos
INSERT INTO `tienda_libros_db`.`libro` (`nombre_libro`, `autor`, `precio`, `existencias`) VALUES ('La palabra del mundo', 'Julio Ramon', '50.00', '4');
INSERT INTO `tienda_libros_db`.`libro` (`nombre_libro`, `autor`, `precio`, `existencias`) VALUES ('Un Mundo para Julius', 'Alfredo Bryce ', '60.00', '7');
INSERT INTO `tienda_libros_db`.`libro` (`nombre_libro`, `autor`, `precio`, `existencias`) VALUES ('Machupicchu', 'Julio C. Valdivia', '80.00', '5');

