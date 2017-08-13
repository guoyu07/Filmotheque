package com.skalvasociety.skalva.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.enumeration.FilmFilterBy;
import com.skalvasociety.skalva.service.IFilmService;

@Controller
@Transactional
//Permet de garder les choix de filtre et de titre de l'utilisateur durant toute sa session
@SessionAttributes( value="filmModel", types={FilmViewModel.class})
public class FilmController {
	
	@Autowired
	IFilmService filmService;		
	
    @ModelAttribute("filmModel")   
    public FilmViewModel addFilmModelToSessionScope() {
    	FilmViewModel filmModel = new FilmViewModel(filmService);     	
    	return filmModel;
    }
	
    @RequestMapping(value = {"/films" }, method = RequestMethod.GET)
    public String listFilm(
    		@RequestParam(value="numPage",required = false ) Integer numPage ,
    		@RequestParam(value="clearFiltre",required = false ) FilmFilterBy clearFiltre ,    		
    		@ModelAttribute("filmModel") FilmViewModel filmModel		
    		) {
    	
    	if(numPage != null && numPage !=0){
    		filmModel.setCurrentPage(numPage);
    	}
    	filmModel.setClearFiltre(clearFiltre);
    	filmModel.refreshModel();   	    	
        return "films";
    }    

	@RequestMapping(value="/filmDetails" ,method = RequestMethod.GET)
	public String filmById(@RequestParam(value="idFilm") Integer idFilm, ModelMap model){	
		if (idFilm == null)
			return "redirect:/films";
		Film film = filmService.getByKey(idFilm);
		if(film != null){
			model.addAttribute("film", film);
			model.addAttribute("dureeFormatee", filmService.getDureeFormatee(film));			
			
			List<Pays> listPays = film.getPays();
			for (Pays pays : listPays) {
				pays.getNom();
			}
			model.addAttribute("pays" , listPays);
			
			List<Video> listVideos = film.getVideos();
			for (Video video : listVideos) {
				video.getNom();
				video.getSiteVideo();
				video.getCleVideo();
			}
			model.addAttribute("videos", listVideos);
			
			List<FilmPersonnage> listPersonnage = film.getPersonnages();
			for (FilmPersonnage filmPersonnage : listPersonnage) {
				filmPersonnage.getActeur().getNom();
				filmPersonnage.getActeur().getPhoto();
				filmPersonnage.getActeur().getIdTMDB();
			}			
			model.addAttribute("personnages", listPersonnage);
			
			List<Realisateur> listRealisateurs = film.getRealisateurs();
			for (Realisateur realisateur : listRealisateurs) {
				realisateur.getId();
				realisateur.getNom();
				realisateur.getPhoto();
			}		
			model.addAttribute("realisateurs",listRealisateurs );
			return "filmDetails";
		}else{
			return "redirect:/films";
		}		
	}
}
		    		