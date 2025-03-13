-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.11.6-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para menopausia
CREATE DATABASE IF NOT EXISTS `menopausia` /*!40100 DEFAULT CHARACTER SET utf32 COLLATE utf32_spanish_ci */;
USE `menopausia`;

-- Volcando estructura para tabla menopausia.histclinica
CREATE TABLE IF NOT EXISTS `histclinica` (
  `idLineaHistClinica` int(11) NOT NULL AUTO_INCREMENT,
  `idPaciente` int(11) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  `P1` int(3) DEFAULT NULL COMMENT 'Peso',
  `P2` int(3) DEFAULT NULL COMMENT 'Presion arterial',
  `P3` int(3) DEFAULT NULL COMMENT 'Presion arterial',
  `P4` int(3) DEFAULT NULL COMMENT 'Cintura',
  `P5` int(3) DEFAULT NULL COMMENT 'Trigliceridos',
  `P6` int(3) DEFAULT NULL COMMENT 'Glucemia',
  `P7` int(3) DEFAULT NULL COMMENT 'FSH',
  `P8` int(3) DEFAULT NULL COMMENT 'Estradiol',
  PRIMARY KEY (`idLineaHistClinica`),
  KEY `Paciente hist clinica` (`idPaciente`),
  CONSTRAINT `Paciente hist clinica` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla menopausia.histclinicafamiliar
CREATE TABLE IF NOT EXISTS `histclinicafamiliar` (
  `idHistClinicaFam` int(11) NOT NULL,
  `idPaciente` int(11) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL COMMENT 'Fecha del alta de la historia clinica familiar',
  `P27` varchar(2) DEFAULT NULL,
  `E27` varchar(30) DEFAULT NULL,
  `P28` varchar(2) DEFAULT NULL,
  `E28` varchar(30) DEFAULT NULL,
  `P29` varchar(2) DEFAULT NULL,
  `E29` varchar(30) DEFAULT NULL,
  `P30` varchar(2) DEFAULT NULL,
  `E30` varchar(30) DEFAULT NULL,
  `P31` varchar(2) DEFAULT NULL,
  `P32` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`idHistClinicaFam`),
  KEY `Paciente en Hist Clinic Fam` (`idPaciente`),
  CONSTRAINT `Paciente en Hist Clinic Fam` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla menopausia.histclinicafija
CREATE TABLE IF NOT EXISTS `histclinicafija` (
  `idHistClinicaFija` int(11) NOT NULL AUTO_INCREMENT,
  `idPaciente` int(11) DEFAULT NULL,
  `fechaHistClinica` date DEFAULT NULL,
  `P1` date DEFAULT NULL,
  `P2` date DEFAULT NULL,
  `P3` date DEFAULT NULL,
  `P4` varchar(2) DEFAULT NULL,
  `P5` varchar(2) DEFAULT NULL,
  `P6` varchar(2) DEFAULT NULL,
  `P7` varchar(2) DEFAULT NULL,
  `P8` varchar(2) DEFAULT NULL,
  `P9` varchar(2) DEFAULT NULL,
  `P10` varchar(2) DEFAULT NULL,
  `P11` varchar(2) DEFAULT NULL,
  `P12` varchar(2) DEFAULT NULL,
  `P13` varchar(2) DEFAULT NULL,
  `P14` varchar(2) DEFAULT NULL,
  `P15` varchar(2) DEFAULT NULL,
  `P16` varchar(2) DEFAULT NULL,
  `P17` varchar(2) DEFAULT NULL,
  `P18` varchar(2) DEFAULT NULL COMMENT 'Pregunta 18. Problema mental',
  `E18` varchar(200) DEFAULT NULL,
  `P19` varchar(2) DEFAULT NULL COMMENT 'Pregunta 19. Diabetes',
  `E19` varchar(2) DEFAULT NULL COMMENT 'Tipo Diabetes',
  `D19` date DEFAULT NULL COMMENT 'Fecha del diagnostico de la pregunta 19',
  `P20` varchar(2) DEFAULT NULL COMMENT 'Alergia a medicamento',
  `E20A` varchar(200) DEFAULT NULL COMMENT 'Cual',
  `E20B` varchar(200) DEFAULT NULL COMMENT 'Reacciones',
  `P21` varchar(200) DEFAULT NULL,
  `P22` varchar(200) DEFAULT NULL,
  `P23` varchar(2) DEFAULT NULL,
  `E23` varchar(200) DEFAULT NULL,
  `P24` varchar(2) DEFAULT NULL,
  `P25` varchar(2) DEFAULT NULL,
  `E25` varchar(200) DEFAULT NULL,
  `P26` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`idHistClinicaFija`),
  KEY `Paciente en Hist Clin Fija` (`idPaciente`),
  CONSTRAINT `Paciente en Hist Clin Fija` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla menopausia.paciente
CREATE TABLE IF NOT EXISTS `paciente` (
  `idPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `Apellidos` varchar(50) DEFAULT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `FechaNac` date DEFAULT NULL,
  PRIMARY KEY (`idPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
