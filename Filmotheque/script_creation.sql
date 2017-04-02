﻿--create database filmotheque;

CREATE TABLE fichier
(
  id serial NOT NULL,
  chemin character varying(500),
  CONSTRAINT prk_constraint_fichier PRIMARY KEY (id),
  CONSTRAINT unique_constraint_operation UNIQUE (chemin)
);

CREATE TABLE GENRE
(
	id serial NOT NULL,
	idTMDB Integer,
	libelle varchar(255),
	CONSTRAINT prk_constraint_genre PRIMARY KEY (id),
	CONSTRAINT unique_constraint_genre UNIQUE (idTMDB)
);

CREATE TABLE PAYS
(
	id serial NOT NULL,
	idIso varchar(10),
	Nom varchar(255),
	CONSTRAINT prk_constraint_pays PRIMARY KEY (id),
	CONSTRAINT unique_constraint_pays UNIQUE (idIso)
);

CREATE TABLE ACTEUR
(
	id serial NOT NULL,
	idTMDB Integer,
	nom varchar(255),
	photo varchar(255),
	biographie varchar(5000),
	dateNaissance varchar(255),
	dateDeces varchar(255),
	lieuNaissance varchar(255),
	CONSTRAINT prk_constraint_personnage PRIMARY KEY (id),
	CONSTRAINT unique_constraint_personnage UNIQUE (idTMDB)
);

CREATE TABLE REALISATEUR
(
	id serial NOT NULL,
	idTMDB Integer,
	nom varchar(255),
	photo varchar(255),
	biographie varchar(5000),
	dateNaissance varchar(255),
	dateDeces varchar(255),
	lieuNaissance varchar(255),
	CONSTRAINT prk_constraint_REALISATEUR PRIMARY KEY (id),
	CONSTRAINT unique_constraint_REALISATEUR UNIQUE (idTMDB)
);

CREATE TABLE MediaTMDB
(
	id serial NOT NULL,
	idTMDB Integer,
	titre varchar(255),
	titreOriginal varchar(255),
	resume varchar(5000),
	affiche varchar(255),
	cleVideo varchar(255),
	siteVideo varchar(255),
	resumeCourt varchar(500),
	dateSortie varchar(255),
	CONSTRAINT prk_constraint_MEDIA PRIMARY KEY (id)
);

CREATE TABLE VIDEO
(
	id serial NOT NULL,
	nom varchar(255),
	cleVideo varchar(255),
	siteVideo varchar(255),
	idMedia Integer,
	CONSTRAINT prk_constraint_VIDEO PRIMARY KEY (id),
	CONSTRAINT fk_VIDEO_MEDIA FOREIGN KEY (idMedia)
		REFERENCES MediaTMDB(id)
);


CREATE TABLE FILM
(
	id serial NOT NULL,	
	idFichier Integer,
	popularite float,
	note float,
	duree Integer,
	CONSTRAINT prk_constraint_film PRIMARY KEY (id),	
	CONSTRAINT fk_film_idFichier FOREIGN KEY (idFichier)
		REFERENCES fichier(id) ,
	CONSTRAINT fk_film_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
);

CREATE TABLE FILM_GENRE
(
	idFilm Integer,
	idGenre Integer,
	CONSTRAINT fk_FILM_GENRE_idFilm FOREIGN KEY (idFilm)
		REFERENCES FILM(id),
	CONSTRAINT fk_FILM_GENRE_idGenre FOREIGN KEY (idGenre)
		REFERENCES GENRE(id)
);

CREATE TABLE FILM_PERSONNAGE
(
	id serial NOT NULL,
	idFilm Integer,
	idActeur Integer,
	nom varchar(255),
	CONSTRAINT prk_constraint_FILM_PERSONNAGE PRIMARY KEY (id),
	CONSTRAINT fk_FILM_personnage_idFilm FOREIGN KEY (idFilm)
		REFERENCES FILM(id),
	CONSTRAINT fk_FILM_personnage_idActeur FOREIGN KEY (idActeur)
		REFERENCES Acteur(id)
);

CREATE TABLE FILM_REALISATEUR
(
	idFilm Integer,
	idREALISATEUR Integer,
	CONSTRAINT fk_FILM_REALISATEUR_idFilm FOREIGN KEY (idFilm)
		REFERENCES FILM(id),
	CONSTRAINT fk_FILM_REALISATEUR_idREALISATEUR FOREIGN KEY (idREALISATEUR)
		REFERENCES REALISATEUR(id)
);


CREATE TABLE SERIE
(
	id serial NOT NULL,	
	popularite float,
	note float,
	CONSTRAINT prk_constraint_serie PRIMARY KEY (id),	
	CONSTRAINT fk_SERIE_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
 
);

CREATE TABLE SERIE_GENRE
(
	idSerie Integer,
	idGenre Integer,
	CONSTRAINT fk_SERIE_GENRE_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id),
	CONSTRAINT fk_SERIE_GENRE_idGenre FOREIGN KEY (idGenre)
		REFERENCES GENRE(id)
);

CREATE TABLE SERIE_PERSONNAGE
(
	id serial NOT NULL,
	idSerie Integer,
	idActeur Integer,
	nom varchar(255),
	CONSTRAINT prk_constraint_SERIE_PERSONNAGE PRIMARY KEY (id),
	CONSTRAINT fk_SERIE_personnage_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id),
	CONSTRAINT fk_SERIE_personnage_idActeur FOREIGN KEY (idActeur)
		REFERENCES Acteur(id)
);


CREATE TABLE SERIE_REALISATEUR
(
	idSERIE Integer,
	idREALISATEUR Integer,
	CONSTRAINT fk_SERIE_REALISATEUR_idSERIE FOREIGN KEY (idSERIE)
		REFERENCES SERIE(id),
	CONSTRAINT fk_FILM_REALISATEUR_idREALISATEUR FOREIGN KEY (idREALISATEUR)
		REFERENCES REALISATEUR(id)
);

CREATE TABLE MEDIA_PAYS
(
	idMedia Integer,
	idPays Integer,
	CONSTRAINT fk_MEDIA_PAYS_idMedia FOREIGN KEY (idMedia)
		REFERENCES MediaTMDB(id),
	CONSTRAINT fk_MEDIA_PAYS_idPays FOREIGN KEY (idPays)
		REFERENCES PAYS(id)
);


CREATE TABLE SAISON
(
	id serial NOT NULL,	
	idSerie integer,	
	numero Integer,	
	CONSTRAINT prk_constraint_SAISON PRIMARY KEY (id),
	CONSTRAINT unique_constraint_saison UNIQUE (idSerie,numero),
	CONSTRAINT fk_film_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id),
	CONSTRAINT fk_Saison_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
	
 
);

CREATE TABLE EPISODE
(
	id serial NOT NULL,	
	idFichier Integer,
	idSaison Integer,	
	numero integer,
	CONSTRAINT prk_constraint_episode PRIMARY KEY (id),	
	CONSTRAINT fk_episode_idFichier FOREIGN KEY (idFichier)
		REFERENCES fichier(id),
	CONSTRAINT fk_episode_idSaison FOREIGN KEY (idSaison)
		REFERENCES saison(id) ,
	CONSTRAINT fk_SERIE_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
);

