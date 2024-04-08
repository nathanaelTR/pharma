CREATE DATABASE `db_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `poste` (
  `id` int NOT NULL AUTO_INCREMENT,
  `posteName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `etat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Etat des Fiche de Frait et Hors forfait';

CREATE TABLE `autre_frais` (
  `id_ficheDeFrai` int unsigned NOT NULL,
  `Date` date NOT NULL,
  `libellé` varchar(255) NOT NULL,
  `Montant` int NOT NULL,
  KEY `id_ficheDeFrai_idx` (`id_ficheDeFrai`),
  CONSTRAINT `id_ficheDeFrai` FOREIGN KEY (`id_ficheDeFrai`) REFERENCES `fiche_de_frais` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Autre frais avec les Identifiant de la fiche de frai associé';

CREATE TABLE `fiche_de_frais` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identifiant du Visiteur ',
  `id_visiteur` int NOT NULL,
  `qt_nuité` int NOT NULL,
  `qt_repasMidi` int NOT NULL,
  `qt_kilometrage` int NOT NULL,
  `qt_total_repasMidi` int NOT NULL,
  `qt_total_nuité` int NOT NULL,
  `qt_total_kilo_kilometrage` int NOT NULL,
  `date_validation` date DEFAULT NULL,
  `date_clôturation` date DEFAULT NULL,
  `date_de_Mise_en_remboursement` date DEFAULT NULL,
  `date_remboursement` date DEFAULT NULL,
  `date_DeCreation` date NOT NULL,
  `fk_etat` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_etat_idx` (`fk_etat`),
  KEY `id_visiteur_idx` (`id_visiteur`),
  CONSTRAINT `fk_etat` FOREIGN KEY (`fk_etat`) REFERENCES `etat` (`id`),
  CONSTRAINT `id_visiteur` FOREIGN KEY (`id_visiteur`) REFERENCES `utilisateur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_creation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(512) NOT NULL,
  `fk_poste` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_poste_idx` (`fk_poste`),
  CONSTRAINT `fk_poste` FOREIGN KEY (`fk_poste`) REFERENCES `poste` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Identification Utilisateur';


-- Création d'un utilisateur avec le mot de passe 
INSERT INTO utilisateur(email, mdp) 
	VALUES("nicola@gmail.com", SHA2("2024", 512));