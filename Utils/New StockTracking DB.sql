-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2022 a las 19:46:42
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `stocktracking`
--

CREATE DATABASE stocktracking;

USE stocktracking;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre_categoria` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre_categoria`) VALUES
(1, 'Unguento'),
(2, 'Inyectables'),
(3, 'Ingeribles');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `Nombre` varchar(60) NOT NULL,
  `Descripcion` varchar(254) NOT NULL,
  `Cantidad` float NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_unidad` int(11) NOT NULL,
  `Estado` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `Nombre`, `Descripcion`, `Cantidad`, `id_categoria`, `id_unidad`, `Estado`) VALUES
(1, 'modificado', 'esto es una modificacion', 50, 1, 1, b'1'),
(2, 'ACEITE BOMBA VACIO x 5lts', '', 10, 2, 2, b'1'),
(3, 'ACEITE BOMBA VACIO x 5lts', '', 10, 3, 3, b'1'),
(25, 'ACEITE BOMBA VACIO x 5lts', '', 10, 3, 1, b'1'),
(26, 'ACEITE BOMBA VACIO x 5lts', '', 10, 3, 1, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `rol_nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `rol_nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidades`
--

CREATE TABLE `unidades` (
  `id` int(11) NOT NULL,
  `nombre_unidad` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `unidades`
--

INSERT INTO `unidades` (`id`, `nombre_unidad`) VALUES
(1, 'litro'),
(2, 'dispositivo'),
(3, 'botella');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido`, `edad`, `email`, `nombre_usuario`, `password`) VALUES
(5, 'waldo', 'cuevas', 24, 'waldo@example.com', 'Waldo', '$2a$10$Tc/6QlTVTZeeHt32UqIxJeqLXhpCvwokbC6TcSVfhPc8.A./tawvq'),
(6, 'prueba', 'prueba', 1, 'prueba@example.com', 'prueba', '$2a$10$els5UmM00g22nsQNP04qzeyc2s19pIaosWnAYnjCxNhdHRrmYSfve'),
(7, 'Juan', 'Perez', 21, 'admin@example.com', 'admin', '$2a$10$qwKe4wW2lgm0x5NrQdQXeO0dW50kU42TnHvZc345qFPkFwl8SdLe.'),
(8, 'Roberto', 'Cediño', 24, 'user@example.com', 'user', '$2a$10$x6Qimw2ATNRTHHiRWOz5lO/uvhDdqWN3rV8eQa8cAficP7xHUWpRi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `usuario_id` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`usuario_id`, `rol_id`) VALUES
(5, 2),
(6, 1),
(6, 2),
(7, 1),
(7, 2),
(8, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`),
  ADD KEY `id_unidad` (`id_unidad`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `unidades`
--
ALTER TABLE `unidades`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_puhr3k3l7bj71hb7hk7ktpxn0` (`nombre_usuario`);

--
-- Indices de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD PRIMARY KEY (`usuario_id`,`rol_id`),
  ADD KEY `FK610kvhkwcqk2pxeewur4l7bd1` (`rol_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `unidades`
--
ALTER TABLE `unidades`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_unidad`) REFERENCES `unidades` (`id`);

--
-- Filtros para la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD CONSTRAINT `FK610kvhkwcqk2pxeewur4l7bd1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`),
  ADD CONSTRAINT `FKbyfgloj439r9wr9smrms9u33r` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
