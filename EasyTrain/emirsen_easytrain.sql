-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql-emirsen.alwaysdata.net
-- Généré le : dim. 29 sep. 2024 à 16:27
-- Version du serveur : 10.6.18-MariaDB
-- Version de PHP : 7.4.33

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
(2, 'Gare de Lyon'),
(6, 'Gare de Lyon Part-Dieu'),
(5, 'Gare de Marseille'),
(4, 'Gare du Nord'),
(3, 'Gare Montparnasse'),
(1, 'Gare Saint-Lazare');

-- --------------------------------------------------------

--
-- Structure de la table `Trajet`
--

CREATE TABLE `Trajet` (
  `id` int(3) NOT NULL,
  `code` varchar(30) NOT NULL,
  `temps_depart` datetime NOT NULL,
  `temps_arrivee` datetime NOT NULL,
  `arret_depart_id` int(3) DEFAULT NULL,
  `arret_arrivee_id` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Trajet`
--

INSERT INTO `Trajet` (`id`, `code`, `temps_depart`, `temps_arrivee`, `arret_depart_id`, `arret_arrivee_id`) VALUES
(1, 'TRAJET001', '2024-09-29 10:00:00', '2024-09-29 10:30:00', 1, 2),
(2, 'TRAJET002', '2024-09-29 11:00:00', '2024-09-29 12:00:00', 3, 4),
(3, 'TRAJET003', '2024-09-29 14:00:00', '2024-09-29 15:00:00', 5, 6);

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
(1, 'emiiir_95', 'monMdpSecret1', 'SEN', 'Emir', '2024-02-29 09:00:00', 'ADMIN'),
(2, 'mamaWata', 'monMdpSecret2', 'Mami', 'Wata', '2024-04-29 09:30:00', 'EMPLOYE'),
(3, 'elPaul', 'monMdpSecret3', 'Martin', 'Paul', '2024-06-29 10:00:00', 'EMPLOYE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Arret`
--
ALTER TABLE `Arret`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `Trajet`
--
ALTER TABLE `Trajet`
  ADD PRIMARY KEY (`id`),
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
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `Trajet`
--
ALTER TABLE `Trajet`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
