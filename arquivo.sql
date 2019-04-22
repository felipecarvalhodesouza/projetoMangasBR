-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 22-Abr-2019 às 18:20
-- Versão do servidor: 10.1.37-MariaDB
-- versão do PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mangasbr`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `collection`
--

CREATE TABLE `collection` (
  `id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `collection`
--

INSERT INTO `collection` (`id`, `owner_id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `collection_title`
--

CREATE TABLE `collection_title` (
  `title_id` int(11) NOT NULL,
  `collection_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `collection_title`
--

INSERT INTO `collection_title` (`title_id`, `collection_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfis`
--

CREATE TABLE `perfis` (
  `user_id` int(11) NOT NULL,
  `perfis` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `perfis`
--

INSERT INTO `perfis` (`user_id`, `perfis`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `publisher`
--

CREATE TABLE `publisher` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `publisher`
--

INSERT INTO `publisher` (`id`, `name`) VALUES
(1, 'Panini'),
(2, 'JBC'),
(3, 'New Pop');

-- --------------------------------------------------------

--
-- Estrutura da tabela `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `review`
--

INSERT INTO `review` (`id`, `date`, `score`, `text`, `user_id`, `title_id`) VALUES
(1, '2018-02-03 15:45:00', 10, 'Naruto é massa', 1, 1),
(2, '2017-01-08 07:45:00', 8, 'Narutão da hora', 1, 1),
(3, '2016-12-19 01:15:00', 7, 'Podia ser melhor', 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `title`
--

CREATE TABLE `title` (
  `id` int(11) NOT NULL,
  `end` datetime DEFAULT NULL,
  `is_finished` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `title`
--

INSERT INTO `title` (`id`, `end`, `is_finished`, `name`, `score`, `start`, `publisher_id`) VALUES
(1, '2015-06-01 00:00:00', b'1', 'Naruto', NULL, '2007-05-01 00:00:00', 1),
(2, '2018-01-01 00:00:00', b'1', 'Your Name', NULL, '2017-08-01 00:00:00', 2),
(3, NULL, b'0', 'No Game No Life', NULL, '2014-12-01 00:00:00', 3),
(4, NULL, b'0', 'Wotakoi', NULL, '2019-03-01 00:00:00', 1),
(5, NULL, b'0', 'Sword Art Online', NULL, '2014-12-01 00:00:00', 1),
(6, NULL, b'0', 'Chobits', NULL, '2014-12-01 00:00:00', 2),
(7, NULL, b'0', 'Re:Zero', NULL, '2018-01-01 00:00:00', 3),
(8, NULL, b'0', 'No Game No Life Desu', NULL, '2014-12-01 00:00:00', 3),
(9, NULL, b'0', 'The Legend Of Zelda', NULL, '2014-12-01 00:00:00', 1),
(10, NULL, b'0', 'Afro Samurai', NULL, '2014-12-01 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `name` varchar(120) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `email`, `is_enabled`, `name`, `senha`) VALUES
(1, 'desouzafelipecarvalho@gmail.com', b'1', 'Felipe Carvalho de Souza', '$2a$10$2KnqN2W8DW8waPApHV1V7.3lhUZxP.1BxL/A6MKz.KSYfN6pkI9pm'),
(2, 'belinhaenois@gmail.com', b'0', 'Isabela de Paula Bernardo', '$2a$10$adiJSHsaHfdHxOPM/wnoSOvJNqVxZ9WR7iF9m83HYIM7EkV6wRCVu');

-- --------------------------------------------------------

--
-- Estrutura da tabela `volume`
--

CREATE TABLE `volume` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `volume`
--

INSERT INTO `volume` (`id`, `date`, `name`, `price`, `title_id`) VALUES
(1, '2007-05-01 00:00:00', 'Volume 1', 8.9, 1),
(2, '2007-06-01 00:00:00', 'Volume 2', 8.9, 1),
(3, '2007-07-01 00:00:00', 'Volume 3', 8.9, 1),
(4, '2007-08-01 00:00:00', 'Volume 4', 8.9, 1),
(5, '2007-09-01 00:00:00', 'Volume 5', 8.9, 1),
(6, '2007-10-01 00:00:00', 'Volume 6', 8.9, 1),
(7, '2007-09-01 00:00:00', 'Volume 1', 10.9, 3),
(8, '2007-10-01 00:00:00', 'Volume 2', 10.9, 3),
(9, '2012-05-01 00:00:00', 'Volume 1', 15.9, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `volume_user`
--

CREATE TABLE `volume_user` (
  `id` int(11) NOT NULL,
  `does_have` bit(1) NOT NULL,
  `collection_id` int(11) DEFAULT NULL,
  `title_id` int(11) DEFAULT NULL,
  `volume_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `volume_user`
--

INSERT INTO `volume_user` (`id`, `does_have`, `collection_id`, `title_id`, `volume_id`) VALUES
(1, b'0', 1, 1, 1),
(2, b'0', 1, 1, 2),
(3, b'0', 1, 1, 3),
(4, b'0', 1, 1, 4),
(5, b'0', 1, 1, 5),
(6, b'0', 1, 1, 6),
(7, b'0', 1, 2, 7),
(8, b'0', 1, 2, 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `collection`
--
ALTER TABLE `collection`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_496k935i20qm8463yenj3b8gg` (`owner_id`);

--
-- Indexes for table `collection_title`
--
ALTER TABLE `collection_title`
  ADD PRIMARY KEY (`collection_id`,`title_id`),
  ADD KEY `FKpxnngamnf9dkkiv2l9pcmcutl` (`title_id`);

--
-- Indexes for table `perfis`
--
ALTER TABLE `perfis`
  ADD KEY `FK2qm6xlefbc3hpirg5oquis5j5` (`user_id`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  ADD KEY `FKrhrgapk611jga18lo4qeguxt8` (`title_id`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcy7tb4c5xk6k4axdpvjlaf870` (`publisher_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `volume`
--
ALTER TABLE `volume`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3xj2jpa310tm9k4buymytsn4y` (`title_id`);

--
-- Indexes for table `volume_user`
--
ALTER TABLE `volume_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa09jv85f91i1f5tt897eirx1t` (`collection_id`,`title_id`),
  ADD KEY `FKob9bd5p1veouaf06byoj3wabe` (`volume_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `collection`
--
ALTER TABLE `collection`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `volume`
--
ALTER TABLE `volume`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `volume_user`
--
ALTER TABLE `volume_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
