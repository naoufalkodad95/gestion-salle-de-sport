CREATE DATABASE SalleDeSport;
USE SalleDeSport;

CREATE TABLE Membres (
    IdMembre INT AUTO_INCREMENT PRIMARY KEY,
    cinMembre VARCHAR(20),
    nomMembre VARCHAR(50),
    prenomMembre VARCHAR(50),
    genreMembre VARCHAR(10),
    dateNaissanceMembre DATE,
    emailMembre VARCHAR(100),
    telephoneMembre VARCHAR(20),
    adresseMembre VARCHAR(200),
    motDePassMembre VARCHAR(100),
    isAdmin BOOLEAN
);

CREATE TABLE Responsables (
    idResponsable INT AUTO_INCREMENT PRIMARY KEY,
    cinResponsable VARCHAR(20),
    nomResponsable VARCHAR(50),
    prenomResponsable VARCHAR(50),
    genreResponsable VARCHAR(10),
    dateNaissanceResponsable DATE,
    emailResponsable VARCHAR(100),
    telephoneResponsable VARCHAR(20),
    adresseResponsable VARCHAR(200),
    motDePasseResponsable VARCHAR(100),
    isAdmin BOOLEAN
);

CREATE TABLE Entraineurs (
    idEntraineur INT AUTO_INCREMENT PRIMARY KEY,
    cinEntraineur VARCHAR(20),
    nomEntraineur VARCHAR(50),
    prenomEntraineur VARCHAR(50),
    genreEntraineur VARCHAR(10),
    dateNaissanceEntraineur DATE,
    emailEntraineur VARCHAR(100),
    telephoneEntraineur VARCHAR(20),
    adresseEntraineur VARCHAR(200),
    specialiteEntraineur VARCHAR(100),
    motDePasseEntraineur VARCHAR(100),
    isAdmin BOOLEAN
);

CREATE TABLE CoursCollectifs (
    idCours INT AUTO_INCREMENT PRIMARY KEY,
    nomCours VARCHAR(100),
    descriptionCours TEXT,
    heureDebutCours TIME,
    heureFinCours TIME,
    joursSemCours VARCHAR(50)
);

CREATE TABLE Reservations (
    idReservation INT AUTO_INCREMENT PRIMARY KEY,
    etatReservation VARCHAR(50),
    dateReservation DATE,
    idCours INT,
    idMembre INT,
    FOREIGN KEY (idCours) REFERENCES CoursCollectifs(idCours),
    FOREIGN KEY (idMembre) REFERENCES Membres(IdMembre)
);

CREATE TABLE Equipements (
    idEquipement INT AUTO_INCREMENT PRIMARY KEY,
    typeEquipement VARCHAR(100),
    etatEquipement VARCHAR(50),
    disponibiliteEquipement BOOLEAN,
    idResponsable INT,
    FOREIGN KEY (idResponsable) REFERENCES Responsables(idResponsable)
);

CREATE TABLE CoursEntraineurs (
    idCours INT,
    idEntraineur INT,
    PRIMARY KEY (idCours, idEntraineur),
    FOREIGN KEY (idCours) REFERENCES CoursCollectifs(idCours),
    FOREIGN KEY (idEntraineur) REFERENCES Entraineurs(idEntraineur)
);

CREATE TABLE EntraineursEquipements (
    idEntraineur INT,
    idEquipement INT,
    PRIMARY KEY (idEntraineur, idEquipement),
    FOREIGN KEY (idEntraineur) REFERENCES Entraineurs(idEntraineur),
    FOREIGN KEY (idEquipement) REFERENCES Equipements(idEquipement)
);
