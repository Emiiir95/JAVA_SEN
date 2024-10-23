-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql-emirsen.alwaysdata.net
-- Generation Time: Oct 04, 2024 at 02:32 PM
-- Server version: 10.6.18-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `emirsen_easytrain`
--

-- --------------------------------------------------------

--
-- Table structure for table `Arret`
--

CREATE TABLE `Arret` (
  `id` int(3) NOT NULL,
  `nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Arret`
--

INSERT INTO `Arret` (`id`, `nom`) VALUES
(1, 'Gare Saint-Lazare'),
(2, 'Gare de Lyon'),
(3, 'Gare Montparnasse'),
(4, 'Gare du Nord'),
(5, 'Gare de Marseille'),
(6, 'Gare de Lyon Part-Dieu');

-- --------------------------------------------------------

--
-- Table structure for table `Trajet`
--

CREATE TABLE `Trajet` (
  `code` varchar(30) NOT NULL,
  `temps_depart` datetime NOT NULL,
  `temps_arrivee` datetime NOT NULL,
  `arret_depart_id` int(3) DEFAULT NULL,
  `arret_arrivee_id` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Trajet`
--

INSERT INTO `Trajet` (`code`, `temps_depart`, `temps_arrivee`, `arret_depart_id`, `arret_arrivee_id`) VALUES
('TRAJET001', '2024-09-29 10:00:00', '2024-09-29 10:30:00', 1, 2),
('TRAJET002', '2024-09-29 11:00:00', '2024-09-29 12:00:00', 3, 4),
('TRAJET003', '2024-09-29 14:00:00', '2024-09-29 15:00:00', 5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `id` int(3) NOT NULL,
  `login` varchar(20) NOT NULL,
  `mdp` varchar(256) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `date_embauche` datetime NOT NULL,
  `role` enum('ADMIN','EMPLOYE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id`, `login`, `mdp`, `nom`, `prenom`, `date_embauche`, `role`) VALUES
(1, 'emiiir_95', SHA2('emiiir_95',256), 'SEN', 'Emir', '2024-02-29 09:00:00', 'ADMIN'),
(2, 'mamaWata', SHA2('mamaWata',256), 'Mami', 'Wata', '2024-04-29 09:30:00', 'EMPLOYE'),
(3, 'elPaul', SHA2('elPaul',256), 'Martin', 'Paul', '2024-06-29 10:00:00', 'EMPLOYE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Arret`
--
ALTER TABLE `Arret`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Trajet`
--
ALTER TABLE `Trajet`
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `arret_depart_id` (`arret_depart_id`),
  ADD KEY `arret_arrivee_id` (`arret_arrivee_id`);

--
-- Indexes for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Arret`
--
ALTER TABLE `Arret`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Trajet`
--
ALTER TABLE `Trajet`
  ADD CONSTRAINT `Trajet_ibfk_1` FOREIGN KEY (`arret_depart_id`) REFERENCES `Arret` (`id`),
  ADD CONSTRAINT `Trajet_ibfk_2` FOREIGN KEY (`arret_arrivee_id`) REFERENCES `Arret` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
