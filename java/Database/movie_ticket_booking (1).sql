-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le : lun. 31 mars 2025 à 16:14
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `movie_ticket_booking`
--

-- --------------------------------------------------------

--
-- Structure de la table `cinema`
--

DROP TABLE IF EXISTS `cinema`;
CREATE TABLE IF NOT EXISTS `cinema` (
  `id_Cinema` int NOT NULL AUTO_INCREMENT,
  `Nom_Cinema` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Adresse` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `Numero_Telephone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Nombre_Salle` int NOT NULL,
  PRIMARY KEY (`id_Cinema`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `cinema`
--

INSERT INTO `cinema` (`id_Cinema`, `Nom_Cinema`, `Adresse`, `Numero_Telephone`, `Nombre_Salle`) VALUES
(9, 'Cinéma Lumière ', '10 Ain benian, Alger', '0123456700', 2),
(11, 'Atlas', 'Garden City, Cheraga', '0553100017', 1),
(14, 'CinemaDetail', '789 Boulevard Test', '0123456789', 5),
(16, 'CinéA', 'Avenue 1, VilleA', '0123456789', 5),
(17, 'CinéB', 'Avenue 2, VilleB', '0987654321', 3),
(18, 'CinéTest', '123 Rue Test', '0123456789', 5),
(21, 'Ciné', '123 Rue Test', '0123456789', 5),
(23, 'Ciné44', '123 Rue Test', '0123456789', 5),
(26, 'CinéC', 'Avenue 3, VilleC', '0123456789', 2),
(27, 'CinéD', 'Avenue 4, VilleD', '0987654321', 1),
(28, 'TFG', '123 Rue ABC', '0123456789', 1);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_Commentaire` int NOT NULL AUTO_INCREMENT,
  `Contenu` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `Note` int NOT NULL,
  `Date_Heure` datetime NOT NULL,
  `id_Film` int NOT NULL,
  `id_Utilisateur` int NOT NULL,
  PRIMARY KEY (`id_Commentaire`),
  KEY `fk_commentaire_utilisateur` (`id_Utilisateur`),
  KEY `fk_commentaire_film` (`id_Film`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_Commentaire`, `Contenu`, `Note`, `Date_Heure`, `id_Film`, `id_Utilisateur`) VALUES
(9, 'Excellent film !', 5, '2025-03-28 21:09:53', 3, 19);

-- --------------------------------------------------------

--
-- Structure de la table `film`
--

DROP TABLE IF EXISTS `film`;
CREATE TABLE IF NOT EXISTS `film` (
  `id_Film` int NOT NULL AUTO_INCREMENT,
  `Titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Duree` int NOT NULL,
  `Image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Date_Sortie` date NOT NULL,
  `Limite_Age` int NOT NULL,
  `Nom_Realisateur` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Note` decimal(3,1) NOT NULL,
  `Description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_Utilisateur` int NOT NULL,
  `Genre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_Film`),
  KEY `fk_film_utilisateur` (`id_Utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `film`
--

INSERT INTO `film` (`id_Film`, `Titre`, `Duree`, `Image`, `Date_Sortie`, `Limite_Age`, `Nom_Realisateur`, `Note`, `Description`, `id_Utilisateur`, `Genre`) VALUES
(3, 'Inception', 148, 'inception.jpg', '2010-07-16', 13, 'Christopher Nolan', 8.8, 'Un film sur les rêves.', 13, 'Science-Fiction');

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id_Paiement` int NOT NULL AUTO_INCREMENT,
  `Montant_Total` decimal(10,0) NOT NULL,
  `Date_Heure` datetime NOT NULL,
  `Statut` enum('En attente','Paye','Echoue','') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'En attente',
  `id_Reservation` int NOT NULL,
  `id_Utilisateur` int NOT NULL,
  `Methode` enum('Carte CIB','Carte Edahabia') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_Paiement`),
  KEY `fk_paiement_reservation` (`id_Reservation`),
  KEY `fk_paiement_utilisateur` (`id_Utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id_Paiement`, `Montant_Total`, `Date_Heure`, `Statut`, `id_Reservation`, `id_Utilisateur`, `Methode`) VALUES
(19, 16, '2025-03-28 21:12:08', 'Echoue', 5, 18, 'Carte Edahabia'),
(20, 16, '2025-03-28 21:21:48', 'Echoue', 5, 18, 'Carte Edahabia'),
(21, 16, '2025-03-28 21:27:48', 'Echoue', 5, 18, 'Carte Edahabia'),
(22, 16, '2025-03-28 21:31:54', 'Echoue', 5, 18, 'Carte Edahabia');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_Reservation` int NOT NULL AUTO_INCREMENT,
  `Statut` enum('EN_ATTENTE','CONFIRME','ANNULE','PAYE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Date_Heure` datetime NOT NULL,
  `id_Utilisateur` int NOT NULL,
  PRIMARY KEY (`id_Reservation`),
  KEY `fk_reservation_utilisateur` (`id_Utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_Reservation`, `Statut`, `Date_Heure`, `id_Utilisateur`) VALUES
(4, 'EN_ATTENTE', '2025-03-28 20:51:11', 13),
(5, 'EN_ATTENTE', '2025-03-28 20:53:06', 15),
(6, 'EN_ATTENTE', '2025-03-28 20:54:50', 15),
(8, 'CONFIRME', '2025-03-28 20:57:28', 15),
(9, 'CONFIRME', '2025-03-28 21:03:53', 15);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `id_Salle` int NOT NULL AUTO_INCREMENT,
  `Nom_Salle` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Capacite` int NOT NULL,
  `id_Cinema` int NOT NULL,
  PRIMARY KEY (`id_Salle`),
  KEY `fk_salle_cinema` (`id_Cinema`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id_Salle`, `Nom_Salle`, `Capacite`, `id_Cinema`) VALUES
(9, 'Salle Modifiée', 250, 11),
(12, 'Salle Test', 100, 9),
(15, 'Salle ', 100, 11),
(17, 'Salle X ', 100, 11),
(19, 'Salle X ', 100, 11),
(20, 'Salle One', 200, 11),
(21, 'Salle One', 200, 11),
(22, 'Salle One', 200, 11),
(23, 'Salle One', 200, 11),
(24, 'Salle One', 200, 11),
(25, 'Salle One', 200, 11);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `id_Seance` int NOT NULL AUTO_INCREMENT,
  `Date_Heure` datetime NOT NULL,
  `Langue` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Place_Disponible` int NOT NULL,
  `id_Utilisateur` int NOT NULL,
  `id_Film` int NOT NULL,
  `id_Salle` int NOT NULL,
  PRIMARY KEY (`id_Seance`),
  KEY `fk_seance_utilisateur` (`id_Utilisateur`),
  KEY `fk_seance_film` (`id_Film`),
  KEY `fk_seance_salle` (`id_Salle`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `siege`
--

DROP TABLE IF EXISTS `siege`;
CREATE TABLE IF NOT EXISTS `siege` (
  `id_Siege` int NOT NULL AUTO_INCREMENT,
  `Nom_Siege` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_Salle` int NOT NULL,
  `Statut` enum('Disponible','Reserver','','') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_Siege`),
  KEY `fk_siege_salle` (`id_Salle`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `siege`
--

INSERT INTO `siege` (`id_Siege`, `Nom_Siege`, `id_Salle`, `Statut`) VALUES
(17, 'B2', 9, 'Reserver'),
(18, 'A5', 9, 'Reserver'),
(22, 'A5', 17, 'Disponible'),
(26, 'A5', 17, 'Disponible'),
(28, 'A5', 17, 'Disponible');

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `id_Ticket` int NOT NULL AUTO_INCREMENT,
  `Prix_Siege` decimal(10,2) NOT NULL,
  `Statut` enum('Reserve','Paye','Annule','Libre') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Libre',
  `id_Utilisateur` int NOT NULL,
  `id_Seance` int NOT NULL,
  `id_Reservation` int NOT NULL,
  `id_Siege` int NOT NULL,
  PRIMARY KEY (`id_Ticket`),
  KEY `fk_ticket_utilisateur` (`id_Utilisateur`),
  KEY `fk_ticket_seance` (`id_Seance`),
  KEY `fk_ticket_reservation` (`id_Reservation`),
  KEY `fk_ticket_siege` (`id_Siege`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_Utilisateur` int NOT NULL AUTO_INCREMENT,
  `Nom_Utilisateur` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mot_Passe` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Role` enum('Admin','Client') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Client',
  PRIMARY KEY (`id_Utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_Utilisateur`, `Nom_Utilisateur`, `Mot_Passe`, `Role`) VALUES
(13, 'admin1', 'admin123', 'Admin'),
(15, 'testUser', '12345', 'Client'),
(16, 'testUser', '12345', 'Client'),
(17, 'testUser', '12345', 'Client'),
(18, 'testUser', '12345', 'Client'),
(19, 'testUser', '12345', 'Client');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fk_commentaire_film` FOREIGN KEY (`id_Film`) REFERENCES `film` (`id_Film`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_commentaire_utilisateur` FOREIGN KEY (`id_Utilisateur`) REFERENCES `utilisateur` (`id_Utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `film`
--
ALTER TABLE `film`
  ADD CONSTRAINT `fk_film_utilisateur` FOREIGN KEY (`id_Utilisateur`) REFERENCES `utilisateur` (`id_Utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `fk_paiement_reservation` FOREIGN KEY (`id_Reservation`) REFERENCES `reservation` (`id_Reservation`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_paiement_utilisateur` FOREIGN KEY (`id_Utilisateur`) REFERENCES `utilisateur` (`id_Utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_reservation_utilisateur` FOREIGN KEY (`id_Utilisateur`) REFERENCES `utilisateur` (`id_Utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `fk_salle_cinema` FOREIGN KEY (`id_Cinema`) REFERENCES `cinema` (`id_Cinema`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `fk_seance_film` FOREIGN KEY (`id_Film`) REFERENCES `film` (`id_Film`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_seance_salle` FOREIGN KEY (`id_Salle`) REFERENCES `salle` (`id_Salle`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_seance_utilisateur` FOREIGN KEY (`id_Utilisateur`) REFERENCES `utilisateur` (`id_Utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `siege`
--
ALTER TABLE `siege`
  ADD CONSTRAINT `fk_siege_salle` FOREIGN KEY (`id_Salle`) REFERENCES `salle` (`id_Salle`) ON DELETE CASCADE;

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `fk_ticket_reservation` FOREIGN KEY (`id_Reservation`) REFERENCES `reservation` (`id_Reservation`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ticket_seance` FOREIGN KEY (`id_Seance`) REFERENCES `seance` (`id_Seance`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ticket_siege` FOREIGN KEY (`id_Siege`) REFERENCES `siege` (`id_Siege`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ticket_utilisateur` FOREIGN KEY (`id_Utilisateur`) REFERENCES `utilisateur` (`id_Utilisateur`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
