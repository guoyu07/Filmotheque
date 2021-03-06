package com.skalvasociety.skalva.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.dao.IGenreDao;
import com.skalvasociety.skalva.tmdbObject.GenreTmdb;
import com.skalvasociety.skalva.tmdbObject.Genres;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;

@Service("genreService")
@Transactional
public class GenreService extends AbstractService<Serializable, Genre> implements IGenreService {
	private Logger logger = Logger.getLogger(GenreService.class);
	
	@Autowired
	private IGenreDao dao;
	
	@Autowired
    private Environment environment;
	
	public Genre getGenreByIdTmdb(Integer idTmdb) {		
		return dao.getGenreByIdTmdb(idTmdb);
	}

	public void majGenre() {
		String API_KEY = environment.getProperty("tmdb.API_KEY");
		TMDBRequest tmdbRequest = new TMDBRequest(API_KEY);
		try {
			Genres genres = tmdbRequest.getGenresSeries();
			List<GenreTmdb> genresTmdb = genres.getGenres();
			for (GenreTmdb genreTmdb : genresTmdb) {
				Genre genre = genreTmdb.toGenre();
				if(isUnique(genre.getIdTMDB())){
					save(genre);
				}					
			}	
			genres = tmdbRequest.getGenresFilms();
			genresTmdb = genres.getGenres();
			for (GenreTmdb genreTmdb : genresTmdb) {
				Genre genre = genreTmdb.toGenre();
				if(isUnique(genre.getIdTMDB())){
					save(genre);
				}					
			}
		} catch (IOException e) {			
			logger.error(e.getMessage(), e.getCause());
		}
		
	}
	
	private boolean isUnique(Integer idTmdb){
		boolean result = true;
		if(dao.getGenreByIdTmdb(idTmdb) != null){
			result = false;
		}
		return result;
	}
}
