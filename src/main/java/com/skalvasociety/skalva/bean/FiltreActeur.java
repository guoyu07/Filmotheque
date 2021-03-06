package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.enumeration.ActeurFilterBy;
import com.skalvasociety.skalva.enumeration.ActeurFilterText;

@Transactional
public class FiltreActeur extends AbstractFiltre<ActeurFilterBy,ActeurFilterText> implements IFiltre<Acteur>  {

	public List<Acteur> filtrerListe(List<Acteur> listeAFiltrer) {
		List<Acteur> listToRemove = new LinkedList<Acteur>();	
		
		for (ActeurFilterBy filtre : listeFiltre.keySet()) {
			switch (filtre) {
			case sexe:
				for (Acteur acteur : listeAFiltrer) {	
					if(!listToRemove.contains(acteur)){
						if(acteur.getSexe() != null){
							if(acteur.getSexe().getId() != listeFiltre.get(filtre) ){
								listToRemove.add(acteur);
							}
						}else{
							listToRemove.add(acteur);
						}	
					}									
				}
				break;
			default:
				break;
			}			
		}
		
		for (ActeurFilterText entite : listeFiltreText.keySet()) {
			switch(entite){
			case nom:
				for (Acteur acteur : listeAFiltrer) {
					if(!acteur.getNom().toUpperCase().contains(listeFiltreText.get(entite).toUpperCase())){
						listToRemove.add(acteur);
					}
				}
			}			
		}
		listeAFiltrer.removeAll(listToRemove);		
		return listeAFiltrer;
	}
}
