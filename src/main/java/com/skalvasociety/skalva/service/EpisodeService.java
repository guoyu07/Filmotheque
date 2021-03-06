package com.skalvasociety.skalva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.dao.IEpisodeDao;
import com.skalvasociety.skalva.tmdbObject.EpisodeTMDB;
import com.skalvasociety.skalva.tools.Convert;

@Service("episodeService")
@Transactional
public class EpisodeService extends AbstractService<Integer, Episode> implements IEpisodeService {
	
	@Autowired
	IEpisodeDao episodeDao;

	public void episodeTmdbToEpisode(EpisodeTMDB episodeTMDB, Episode episode) {
		episode.setTitre(episodeTMDB.getName());
		episode.setAffiche(episodeTMDB.getStill_path());
		episode.setIdTMDB(episodeTMDB.getId());
		episode.setNumero(episodeTMDB.getEpisode_number());
		episode.setResume(episodeTMDB.getOverview());
		episode.setDateSortie(new Convert().stringToDate(episodeTMDB.getAir_date()));	
	}

	public Episode getEpisodeBySaisonNumEpisode(Saison saison, Integer numEpisode) {		
		return episodeDao.getEpisodeBySaisonNumEpisode(saison, numEpisode);
	}
}
