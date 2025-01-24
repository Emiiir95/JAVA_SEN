-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : db
-- Généré le : ven. 24 jan. 2025 à 13:06
-- Version du serveur : 11.5.2-MariaDB-ubu2404
-- Version de PHP : 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `emirsen_easytrain`
--

-- --------------------------------------------------------

--
-- Structure de la table `Arret`
--

CREATE TABLE `Arret` (
  `id` int(3) NOT NULL,
  `nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Arret`
--

INSERT INTO `Arret` (`id`, `nom`) VALUES
(1, 'Gare Saint-Lazare'),
(2, 'Gare de Lyon'),
(3, 'Gare Montparnasse'),
(4, 'Gare du Nord'),
(5, 'Gare de Marseille'),
(6, 'Gare de Lyon Part-Dieu'),
(7, 'Paris Gare de Lyon');

-- --------------------------------------------------------

--
-- Structure de la table `Trajet`
--

CREATE TABLE `Trajet` (
  `code` varchar(30) NOT NULL,
  `temps_depart` datetime NOT NULL,
  `temps_arrivee` datetime NOT NULL,
  `arret_depart_id` int(3) DEFAULT NULL,
  `arret_arrivee_id` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Trajet`
--

INSERT INTO `Trajet` (`code`, `temps_depart`, `temps_arrivee`, `arret_depart_id`, `arret_arrivee_id`) VALUES
('TRAJET001', '2024-09-29 10:00:00', '2024-09-29 10:30:00', 1, 2),
('TRAJET002', '2024-09-29 11:00:00', '2024-09-29 12:00:00', 3, 4),
('TRAJET003', '2024-09-29 14:00:00', '2024-09-29 15:00:00', 5, 6),
('TRAJET004', '2024-12-09 08:00:00', '2024-12-09 09:00:00', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
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
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id`, `login`, `mdp`, `nom`, `prenom`, `date_embauche`, `role`) VALUES
(1, 'emiiir_95', '25c5eb05db3eac69dd06942015567f83651601096ef7b423af593c49769124ed', 'SEN', 'Emir', '2024-02-29 09:00:00', 'ADMIN'),
(2, 'mamaWata', '624f136884f83b1270e0980708d445a8dc66a3d95983440829920d61265dd63d', 'Mami', 'Wata', '2024-04-29 09:30:00', 'EMPLOYE'),
(4, 'updateTest', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test', 'update', '2024-10-23 12:00:14', 'EMPLOYE'),
(5, 'testEmploye', '19ad46a0341555136d767353c0d8311280f165ec6621eab6a61ba1f8d429f3f8', 'test', 'employe', '2024-10-23 13:13:03', 'EMPLOYE'),
(6, 'testAdmin', '5ef368722bf09031ced7372c90df64c99a750724ec6dff3e5cf00bb5fcf75c08', 'test', 'Admin', '2024-10-23 13:13:33', 'ADMIN'),
(19, 'jdoe', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Doe', 'John', '2024-12-20 00:00:00', 'EMPLOYE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Arret`
--
ALTER TABLE `Arret`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Trajet`
--
ALTER TABLE `Trajet`
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `arret_depart_id` (`arret_depart_id`),
  ADD KEY `arret_arrivee_id` (`arret_arrivee_id`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Arret`
--
ALTER TABLE `Arret`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Trajet`
--
ALTER TABLE `Trajet`
  ADD CONSTRAINT `Trajet_ibfk_1` FOREIGN KEY (`arret_depart_id`) REFERENCES `Arret` (`id`),
  ADD CONSTRAINT `Trajet_ibfk_2` FOREIGN KEY (`arret_arrivee_id`) REFERENCES `Arret` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
